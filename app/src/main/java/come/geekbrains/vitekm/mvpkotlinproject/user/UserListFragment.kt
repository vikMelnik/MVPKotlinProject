package come.geekbrains.vitekm.mvpkotlinproject.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import come.geekbrains.vitekm.mvpkotlinproject.GeekBrainsApp
import come.geekbrains.vitekm.mvpkotlinproject.core.OnBackPressedListener
import come.geekbrains.vitekm.mvpkotlinproject.databinding.FragmentUserListBinding
import come.geekbrains.vitekm.mvpkotlinproject.model.GithubUser
import come.geekbrains.vitekm.mvpkotlinproject.repository.impl.GithubRepositoryImpl
import come.geekbrains.vitekm.mvpkotlinproject.user.userinterface.UserView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserListFragment: MvpAppCompatFragment(), UserView, OnBackPressedListener {
    companion object {
        fun newInstance() = UserListFragment()

    }

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(GithubRepositoryImpl(), GeekBrainsApp.instance.router)
    }

    private var mAdapter:UsersListAdapter? = null
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
        mAdapter = UsersListAdapter(presenter.usersListPresenter)
        vb?.rvGithubUsers?.adapter = mAdapter

    }

    override fun updateList() {
        mAdapter?.notifyDataSetChanged()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun onBackPressed() = presenter.onBackPressed()
}


