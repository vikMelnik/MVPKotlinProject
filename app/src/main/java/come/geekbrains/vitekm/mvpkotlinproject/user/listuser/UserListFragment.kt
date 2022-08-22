package come.geekbrains.vitekm.mvpkotlinproject.user.listuser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import come.geekbrains.vitekm.mvpkotlinproject.GeekBrainsApp
import come.geekbrains.vitekm.mvpkotlinproject.core.OnBackPressedListener
import come.geekbrains.vitekm.mvpkotlinproject.core.network.NetworkProvider
import come.geekbrains.vitekm.mvpkotlinproject.databinding.FragmentUserListBinding
import come.geekbrains.vitekm.mvpkotlinproject.glide.GlideImageLoader
import come.geekbrains.vitekm.mvpkotlinproject.repository.impl.GithubRepositoryImpl
import come.geekbrains.vitekm.mvpkotlinproject.allinterface.UserView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserListFragment: MvpAppCompatFragment(), UserView, OnBackPressedListener {
    companion object {
        fun newInstance() = UserListFragment()

    }

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(GithubRepositoryImpl(NetworkProvider.usersApi), GeekBrainsApp.instance.router)
    }

    private var mAdapter: UsersListAdapter? = null
    private var vb: FragmentUserListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUserListBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun initList() {
        vb?.rvGithubUsers?.layoutManager = LinearLayoutManager(requireContext())
        mAdapter = UsersListAdapter(presenter.usersListPresenter, GlideImageLoader())
        vb?.rvGithubUsers?.adapter = mAdapter
        vb?.btnGoToImgConverter?.setOnClickListener { presenter.goToImageConverter() }

    }

    override fun updateList() {
        mAdapter?.notifyDataSetChanged()
    }
    override fun showProgressBar() {
        this.vb?.progressBar?.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        vb?.progressBar?.visibility = View.GONE
    }


    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun onBackPressed() = presenter.onBackPressed()
}


