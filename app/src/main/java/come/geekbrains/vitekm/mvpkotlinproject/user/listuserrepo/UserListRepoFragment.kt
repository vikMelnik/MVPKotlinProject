package come.geekbrains.vitekm.mvpkotlinproject.user.listuserrepo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import come.geekbrains.vitekm.mvpkotlinproject.GeekBrainsApp
import come.geekbrains.vitekm.mvpkotlinproject.allinterface.UserInfoView
import come.geekbrains.vitekm.mvpkotlinproject.core.OnBackPressedListener
import come.geekbrains.vitekm.mvpkotlinproject.core.network.NetworkProvider
import come.geekbrains.vitekm.mvpkotlinproject.databinding.FragmentUserRepositoryInfoBinding
import come.geekbrains.vitekm.mvpkotlinproject.glide.GlideImageLoader
import come.geekbrains.vitekm.mvpkotlinproject.model.RetrofitUserRepo
import come.geekbrains.vitekm.mvpkotlinproject.schedulers.MySchedulersFactory

import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserListRepoFragment : MvpAppCompatFragment(), UserInfoView, OnBackPressedListener {

    companion object {
        private const val ARG_USER = "ARG_USER_LOGIN"

        fun newInstance(userLogin: String) =
            UserListRepoFragment().apply { arguments = bundleOf(ARG_USER to userLogin) }
    }

    private val userLogin: String? by lazy {
        arguments?.getString(ARG_USER, "vikMel")
    }

    private var vb: FragmentUserRepositoryInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) =
        FragmentUserRepositoryInfoBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    private val presenter: UserListRepoPresenter by moxyPresenter {
        UserListRepoPresenter(
            userLogin,
            RetrofitUserRepo(NetworkProvider.usersApi),
            MySchedulersFactory.create(),
            GeekBrainsApp.instance.router
        )
    }

    var adapter: UserListRepoAdapter? = null

    override fun showLogin(text: String) {
        vb?.tvLogin?.text = text
    }

    override fun setImageAvatar(url: String): Unit = with(vb) {
        this?.imageViewUserAvatar?.let { GlideImageLoader().loadInfo(url, it) }
    }

    override fun showTopString(text: String) {
        vb?.textViewTopString?.text = text
    }

    override fun showCenterString(text: String) {
        vb?.textViewCenterString?.text = text
    }

    override fun initList() {
        vb?.rvUserRepos?.layoutManager = LinearLayoutManager(context)
        adapter = UserListRepoAdapter(presenter.reposListPresenter)
        vb?.rvUserRepos?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showProgressBar() {
        this.vb?.progressBar?.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        vb?.progressBar?.visibility = View.GONE
    }

    override fun showErrorBar() {
        this.vb?.imageViewError?.visibility = View.VISIBLE
    }

    override fun hideErrorBar() {
        this.vb?.imageViewError?.visibility = View.GONE
    }

    override fun onBackPressed() = presenter.onBackPressed()
}