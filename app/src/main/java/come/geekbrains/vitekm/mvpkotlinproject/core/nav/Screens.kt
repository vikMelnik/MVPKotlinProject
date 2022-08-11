package come.geekbrains.vitekm.mvpkotlinproject.core.nav

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import come.geekbrains.vitekm.mvpkotlinproject.user.loginuserdetail.LoginUserFragment

import come.geekbrains.vitekm.mvpkotlinproject.user.listuser.UserListFragment


object UsersScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return UserListFragment.newInstance()
    }
    fun userInfo(userLogin: String): FragmentScreen =
        FragmentScreen { LoginUserFragment.newInstance(userLogin) }
}

