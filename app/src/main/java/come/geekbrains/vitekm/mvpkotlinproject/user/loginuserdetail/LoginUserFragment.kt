package come.geekbrains.vitekm.mvpkotlinproject.user.loginuserdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import come.geekbrains.vitekm.mvpkotlinproject.GeekBrainsApp
import come.geekbrains.vitekm.mvpkotlinproject.core.OnBackPressedListener
import come.geekbrains.vitekm.mvpkotlinproject.databinding.FragmentLoginUserBinding
import come.geekbrains.vitekm.mvpkotlinproject.repository.impl.GithubRepositoryImpl
import come.geekbrains.vitekm.mvpkotlinproject.user.userinterface.UserInfoView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class LoginUserFragment : MvpAppCompatFragment(), UserInfoView, OnBackPressedListener {


    private lateinit var viewBinding: FragmentLoginUserBinding

    companion object {
        private const val ARG_USER = "ARG_USER_LOGIN"

        fun newInstance(userLogin: String) =
            LoginUserFragment().apply { arguments = bundleOf(ARG_USER to userLogin) }
    }


    private val userLogin: String? by lazy {
        arguments?.getString(ARG_USER, "MrFox")
    }

    private val presenter: LoginUserPresenter by moxyPresenter {
        LoginUserPresenter(userLogin, GithubRepositoryImpl(), GeekBrainsApp.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentLoginUserBinding.inflate(inflater, container, false).also {
            viewBinding = it
        }.root
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBackPressed(): Boolean = presenter.onBackPressed()

    override fun showLogin(text: String) {
        viewBinding.login.text = text
    }

    override fun showProgressBar() {
        this.viewBinding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        viewBinding.progressBar.visibility = View.GONE
    }

    override fun showErrorBar() {
        this.viewBinding.imageViewError.visibility = View.VISIBLE
    }

    override fun hideErrorBar() {
        this.viewBinding.imageViewError.visibility = View.GONE
    }
}