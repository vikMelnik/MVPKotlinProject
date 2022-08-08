package come.geekbrains.vitekm.mvpkotlinproject.user

import com.github.terrakok.cicerone.Router
import come.geekbrains.vitekm.mvpkotlinproject.repository.GithubRepository
import moxy.MvpPresenter

class UserPresenter(
    private val repository: GithubRepository,
    private val router: Router
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initList(repository.getUsers())
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}
