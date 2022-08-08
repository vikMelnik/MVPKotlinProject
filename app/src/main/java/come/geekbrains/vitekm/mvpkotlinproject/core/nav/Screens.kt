package come.geekbrains.vitekm.mvpkotlinproject.core.nav

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import come.geekbrains.vitekm.mvpkotlinproject.user.UserFragment


object UsersScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return UserFragment.getInstance()
    }
}
//data class UsersScreen(val login: String) : FragmentScreen {
//    override fun createFragment(factory: FragmentFactory): Fragment {
//        return UserFragment.getInstance(login)
//    }
//}