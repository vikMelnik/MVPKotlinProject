package come.geekbrains.vitekm.mvpkotlinproject.user.listuserrepo

import com.github.terrakok.cicerone.Router
import come.geekbrains.vitekm.mvpkotlinproject.allinterface.IUserRepoListPresenter
import come.geekbrains.vitekm.mvpkotlinproject.allinterface.RepoItemView
import come.geekbrains.vitekm.mvpkotlinproject.allinterface.UserInfoView
import come.geekbrains.vitekm.mvpkotlinproject.core.nav.UsersScreen
import come.geekbrains.vitekm.mvpkotlinproject.model.GithubUserNew
import come.geekbrains.vitekm.mvpkotlinproject.model.Repository
import come.geekbrains.vitekm.mvpkotlinproject.model.RetrofitUserRepo
import come.geekbrains.vitekm.mvpkotlinproject.schedulers.MySchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UserListRepoPresenter(
    private val userLogin: String? = null,
    private val usersRepo: RetrofitUserRepo,
    private val schedulers: MySchedulers,
    private val router: Router
) : MvpPresenter<UserInfoView>() {
    private val disposables = CompositeDisposable()

    class ReposListPresenter : IUserRepoListPresenter {
        val repositories = mutableListOf<Repository>()
        override var itemClickListener: ((RepoItemView) -> Unit)? = null
        override fun getCount() = repositories.size

        override fun bindView(view: RepoItemView) {
            val repos = repositories[view.pos]
            repos.name.let {
                view.setName(repos.name)
                repos.description?.let { view.setDescription((repos.description)) }
            }
        }
    }

    val reposListPresenter = ReposListPresenter()

    override fun onFirstViewAttach() {

        if (userLogin != null) {
            viewState.initList()
            viewState.hideErrorBar()
            viewState.showProgressBar()
            usersRepo
                .getUserByLogin(userLogin)
                .observeOn(schedulers.main())
                .subscribe(object : SingleObserver<GithubUserNew> {
                    override fun onSubscribe(d: Disposable?) {
                        disposables.add(d)
                    }

                    override fun onSuccess(userInfo: GithubUserNew?) {
                        userInfo?.let {
                            onGetUserByLoginSuccess(it)
                        }
                    }

                    override fun onError(e: Throwable?) {
                        onGetUserByLoginError(e)
                    }
                })
        }
    }

    fun onGetUserByLoginSuccess(userInfo: GithubUserNew) {
        viewState.showLogin(userInfo.login)
        viewState.setImageAvatar(userInfo.avatarUrl)
        usersRepo
            .getUserRepos(userInfo.login, null, null, null, 99, 1)
            .observeOn(schedulers.main())
            .subscribe(object : SingleObserver<List<Repository>> {
                override fun onSubscribe(d: Disposable?) {
                    disposables.add(d)
                }

                override fun onSuccess(t: List<Repository>) {
                    onGetUserReposSuccess(t, userInfo)
                }

                override fun onError(e: Throwable?) {
                    onGetUserReposError(e)
                }
            })
        viewState.showCenterString(userInfo.htmlUrl)
        viewState.hideProgressBar()
    }

    fun onGetUserByLoginError(e: Throwable?) {
        viewState.hideProgressBar()
        viewState.showErrorBar()
    }

    fun onGetUserReposSuccess(userRepositories: List<Repository>, userInfo: GithubUserNew) {
        viewState.showTopString(
            "Загружено репозиториев :"
                    + userRepositories.size + " из " + userInfo.publicRepos
        )
        reposListPresenter.repositories.addAll(userRepositories)
        reposListPresenter.itemClickListener = { itemView ->
            router.navigateTo(UsersScreen.userInfo(userRepositories[itemView.pos].url))
        }
        viewState.updateList()
    }

    fun onGetUserReposError(e: Throwable?) {
        viewState.showTopString("Ошибка при попытке чтения спискаа репозиториев")
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        disposables.clear()
    }
}