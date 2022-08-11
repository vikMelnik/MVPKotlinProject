package come.geekbrains.vitekm.mvpkotlinproject.user.loginuserdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Screen
import come.geekbrains.vitekm.mvpkotlinproject.databinding.FragmentLoginUserBinding
import come.geekbrains.vitekm.mvpkotlinproject.model.GithubUser


class LoginUserFragment : Fragment(), Screen {


    private lateinit var viewBinding: FragmentLoginUserBinding

    companion object {
        private const val ARG_USER = "ARG_USER_LOGIN"

        fun newInstance(userLogin: String) =
            LoginUserFragment().apply { arguments = bundleOf(ARG_USER to userLogin) }
    }


    private val userLogin: String? by lazy {
        arguments?.getString(ARG_USER, "MrFox")
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<GithubUser>(ARG_USER)?.let {
           viewBinding.login.text = userLogin
        }
    }



    override fun onDestroy() {
        super.onDestroy()
    }

}