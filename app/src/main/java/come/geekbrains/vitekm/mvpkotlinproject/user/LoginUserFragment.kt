package come.geekbrains.vitekm.mvpkotlinproject.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Screen
import come.geekbrains.vitekm.mvpkotlinproject.databinding.FragmentLoginUserBinding


class LoginUserFragment : Fragment(), Screen {


    private lateinit var viewBinding: FragmentLoginUserBinding

    companion object {
        private const val ARG_USER = "ARG_USER_LOGIN"

        fun newInstance(userLogin: String) =
            LoginUserFragment().apply { arguments = bundleOf(ARG_USER to userLogin) }
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

}