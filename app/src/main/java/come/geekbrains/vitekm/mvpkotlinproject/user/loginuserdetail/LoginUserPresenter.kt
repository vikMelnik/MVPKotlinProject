package come.geekbrains.vitekm.mvpkotlinproject.user.loginuserdetail

import com.github.terrakok.cicerone.Router
import come.geekbrains.vitekm.mvpkotlinproject.model.GithubUser
import come.geekbrains.vitekm.mvpkotlinproject.repository.impl.GithubRepositoryImpl
import come.geekbrains.vitekm.mvpkotlinproject.repository.impl.GithubRepositoryImplOld
import moxy.MvpPresenter
import come.geekbrains.vitekm.mvpkotlinproject.user.userinterface.UserInfoView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable


class LoginUserPresenter(
    private val userId: String? = null,
    private val repository: GithubRepositoryImpl,
    private val router: Router,
) : MvpPresenter<UserInfoView>() {

    val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        if (userId != null) {
            viewState.hideErrorBar()
            viewState.showProgressBar()
            repository.getUserById(userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    object : SingleObserver<GithubUser> {
                        override fun onSubscribe(d: Disposable) {
                            disposables.add(d)
                        }

                        override fun onError(e: Throwable) {
                            viewState.showLogin("ERROR")
                            viewState.hideProgressBar()
                            viewState.showErrorBar()
                        }

                        override fun onSuccess(t: GithubUser) {
                            if (t != null) {
                                t.let { viewState.showLogin(it.login)
                                viewState.setImageAvatar(it.avatarUrl)}
                                viewState.hideProgressBar()
                            }
                        }

                    }
                )
        }
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        disposables.clear()
    }

}
