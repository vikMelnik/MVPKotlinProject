package come.geekbrains.vitekm.mvpkotlinproject.user.loginuserdetail

import com.github.terrakok.cicerone.Router
import come.geekbrains.vitekm.mvpkotlinproject.allinterface.RepoInfoView
import moxy.MvpPresenter
import come.geekbrains.vitekm.mvpkotlinproject.model.Repository
import come.geekbrains.vitekm.mvpkotlinproject.model.RetrofitUserRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

class LoginUserPresenter(
    private val repositoryUrl: String? = null,
    private val usersRepo: RetrofitUserRepo,
    private val router: Router,
) : MvpPresenter<RepoInfoView>() {

    val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        if (repositoryUrl != null) {
            viewState.initList()
            viewState.hideErrorBar()
            viewState.showProgressBar()
           usersRepo
                .getRepositoryByUrl(repositoryUrl)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<Repository> {
                    override fun onSubscribe(d: Disposable?) {
                        disposables.add(d)
                    }

                    override fun onSuccess(repoInfo: Repository?) {
                        onGetRepositorySuccess(repoInfo)
                    }

                    override fun onError(e: Throwable?) {
                        onGetRepositoryError(e)
                    }
                })
        }
    }

    private fun onGetRepositorySuccess(repoInfo: Repository?) {
        repoInfo?.let {
            viewState.showLogin(it.owner.login)
            viewState.setImageAvatar(it.owner.avatarUrl)
            viewState.showNameRepository(it.name)
            viewState.showDescriptionRepository(
                it.description
                        + " \nЗвездный рейтинг: " + it.stargazersCount
                        + " \nКоличество наблюдателей: " + it.watchersCount
            )
            viewState.showCountFork("Количество форков: " + it.forksCount)
            viewState.hideProgressBar()
        }
    }

    private fun onGetRepositoryError(e: Throwable?) {
        viewState.hideProgressBar()
        viewState.showErrorBar()
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        disposables.clear()
    }
}
