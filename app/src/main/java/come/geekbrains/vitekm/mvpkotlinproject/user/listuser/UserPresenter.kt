package come.geekbrains.vitekm.mvpkotlinproject.user.listuser

import com.github.terrakok.cicerone.Router
import come.geekbrains.vitekm.mvpkotlinproject.core.nav.UsersScreen
import come.geekbrains.vitekm.mvpkotlinproject.model.GithubUser
import come.geekbrains.vitekm.mvpkotlinproject.repository.GithubRepository
import come.geekbrains.vitekm.mvpkotlinproject.user.userinterface.IUserListPresenter
import come.geekbrains.vitekm.mvpkotlinproject.user.userinterface.UserItemView
import come.geekbrains.vitekm.mvpkotlinproject.user.userinterface.UserView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UserPresenter(
    private val repository: GithubRepository,
    private val router: Router,
) : MvpPresenter<UserView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login.let {
                view.setLogin(user.login)
                view.setImageAvatar(user.avatarUrl)
            }
        }

        override fun getCount() = users.size

    }

    val usersListPresenter = UsersListPresenter()
    val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initList()
        viewState.showProgressBar()
        repository.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<GithubUser>> {
                override fun onSubscribe(d: Disposable?) {
                    disposables.add(d)
                }

                override fun onSuccess(t: List<GithubUser>?) {
                    if (t != null) {
                        viewState.hideProgressBar()
                        usersListPresenter.users.addAll(t)
                        usersListPresenter.itemClickListener = { itemView ->
                            router.navigateTo(UsersScreen.userInfo(t[itemView.pos].login))
                        }
                        viewState.updateList()
                    }
                }

                override fun onError(e: Throwable?) {
                    viewState.hideProgressBar()
                }
            })
    }

    fun goToImageConverter() {
        router.navigateTo(UsersScreen.imageConverter())
    }
    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
    override fun onDestroy() {
        disposables.clear()
    }
}


