package come.geekbrains.vitekm.mvpkotlinproject.user.loginuserdetail

import com.github.terrakok.cicerone.Router
import come.geekbrains.vitekm.mvpkotlinproject.repository.GithubRepository
import moxy.MvpPresenter
import come.geekbrains.vitekm.mvpkotlinproject.user.userinterface.UserInfoView

class LoginUserPresenter (
    private val userid: String? = null,
    private val repository: GithubRepository,
    private val router: Router
): MvpPresenter<UserInfoView>() {

    override fun onFirstViewAttach() {
        val currentUser = repository.getUsers().firstOrNull { it.login == userid }
        currentUser?.let { viewState.showLogin(it.login) }

    }
    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

}


