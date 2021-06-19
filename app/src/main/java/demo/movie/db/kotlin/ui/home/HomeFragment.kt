package demo.movie.db.kotlin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import demo.movie.db.kotlin.databinding.FragmentMoviedbHomeBinding
import demo.movie.db.kotlin.databinding.FragmentMoviedbLoginBinding
import demo.movie.db.kotlin.utils.SharedPreferencesHelper

class HomeFragment : Fragment(), View.OnClickListener {
    private lateinit var bindingView: FragmentMoviedbHomeBinding
    private lateinit var sharedPreferences: SharedPreferencesHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        bindingView = FragmentMoviedbHomeBinding.inflate(layoutInflater, container, false)
        sharedPreferences = SharedPreferencesHelper(requireContext())


        return bindingView.root
    }

    override fun onClick(clickedView: View) {
        when(clickedView.id) {
            /*R.id.ic_arrow_back -> {
                findNavController().navigate(R.id.splash_screen)
            }*/
        }
    }
}
