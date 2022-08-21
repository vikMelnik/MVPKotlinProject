package come.geekbrains.vitekm.mvpkotlinproject.core.nav

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import come.geekbrains.vitekm.mvpkotlinproject.imageconverter.ImageConverterFragment


import come.geekbrains.vitekm.mvpkotlinproject.user.listuser.UserListFragment
import come.geekbrains.vitekm.mvpkotlinproject.user.listuserrepo.UserListRepoFragment
import come.geekbrains.vitekm.mvpkotlinproject.user.loginuserdetail.LoginUserFragment


object UsersScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return UserListFragment.newInstance()
    }
    fun userInfo(userLogin: String): FragmentScreen =
        FragmentScreen { LoginUserFragment.newInstance(userLogin) }

    fun imageConverter(): Screen =
        FragmentScreen { ImageConverterFragment() }

    fun repoInfo(repositoryUrl: String): Screen =
        FragmentScreen { UserListRepoFragment.newInstance(repositoryUrl) }

}

