package demo.movie.db.kotlin.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import demo.movie.db.kotlin.R
import demo.movie.db.kotlin.databinding.FragmentMoviedbLoginBinding
import demo.movie.db.kotlin.utils.SharedPreferencesHelper

class LoginFragment : Fragment(), View.OnClickListener {
    private lateinit var bindingView: FragmentMoviedbLoginBinding
    private lateinit var sharedPreferences: SharedPreferencesHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        bindingView = FragmentMoviedbLoginBinding.inflate(layoutInflater, container, false)
        sharedPreferences = SharedPreferencesHelper(requireContext())


        bindingView.loginButton.setOnClickListener(this)
        return bindingView.root
    }

    override fun onClick(clickedView: View) {
        when(clickedView.id) {
            R.id.login_button -> {
                findNavController().navigate(R.id.movie_home)
            }
        }
    }
}
