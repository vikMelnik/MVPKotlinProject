package come.geekbrains.vitekm.mvpkotlinproject.main



import com.github.terrakok.cicerone.Router
import come.geekbrains.vitekm.mvpkotlinproject.core.nav.UsersScreen
import moxy.MvpPresenter

class MainPresenter(
    private val router: Router
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(UsersScreen)
    }

    fun onBackPressed() {
        router.exit()
    }
}
