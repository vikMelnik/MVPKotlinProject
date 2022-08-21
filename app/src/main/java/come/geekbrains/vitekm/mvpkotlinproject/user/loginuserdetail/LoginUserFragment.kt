package come.geekbrains.vitekm.mvpkotlinproject.user.loginuserdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import come.geekbrains.vitekm.mvpkotlinproject.GeekBrainsApp
import come.geekbrains.vitekm.mvpkotlinproject.allinterface.RepoInfoView
import come.geekbrains.vitekm.mvpkotlinproject.core.OnBackPressedListener
import come.geekbrains.vitekm.mvpkotlinproject.core.network.NetworkProvider
import come.geekbrains.vitekm.mvpkotlinproject.databinding.FragmentLoginUserBinding
import come.geekbrains.vitekm.mvpkotlinproject.glide.GlideImageLoader
import come.geekbrains.vitekm.mvpkotlinproject.model.RetrofitUserRepo
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class LoginUserFragment : MvpAppCompatFragment(), RepoInfoView, OnBackPressedListener {


    private lateinit var viewBinding: FragmentLoginUserBinding

    companion object {
        private const val ARG_REPO = "ARG_REPO_URL"

        fun newInstance(repositoryUrl: String) =
            LoginUserFragment().apply { arguments = bundleOf(ARG_REPO to repositoryUrl) }
    }


    private val repositoryUrl: String? by lazy {
        arguments?.getString(ARG_REPO, "VikMel")
    }

    private val presenter: LoginUserPresenter by moxyPresenter {
        LoginUserPresenter(repositoryUrl,
            RetrofitUserRepo(NetworkProvider.usersApi),
            GeekBrainsApp.instance.router)
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
        viewBinding.tvLogin.text = text

    }

    override fun setImageAvatar(url: String): Unit = with(viewBinding) {
        this?.ivAvatar?.let { GlideImageLoader().loadInfo(url, it) }
    }

    override fun showNameRepository(text: String) {
        viewBinding.tvNameRepository.text = text
    }

    override fun showDescriptionRepository(text: String) {
        viewBinding.tvDescription.text = text
    }

    override fun showCountFork(count: String) {
        viewBinding.tvCountOfForks.text = count
    }

    override fun initList() {
        showCountFork("Количество форков: 0")
        showNameRepository("Загрузка...")
    }

    override fun showProgressBar() {
        this.viewBinding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        this.viewBinding.progressBar.visibility = View.GONE
    }

    override fun showErrorBar() {
        this.viewBinding.imageViewError.visibility = View.VISIBLE
    }

    override fun hideErrorBar() {
        this.viewBinding.imageViewError.visibility = View.GONE
    }
}