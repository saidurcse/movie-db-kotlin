package demo.movie.db.kotlin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import demo.movie.db.kotlin.MainActivity
import demo.movie.db.kotlin.databinding.FragmentMoviedbHomeBinding
import demo.movie.db.kotlin.utils.SharedPreferencesHelper
import demo.movie.db.kotlin.utils.SharedPreferencesKey
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), View.OnClickListener {

    private val moviesViewModel by viewModel<HomeListViewModel>()
    private lateinit var bindingView: FragmentMoviedbHomeBinding
    private val adapter = MovieAdapter()
    private lateinit var sharedPreferences: SharedPreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingView = FragmentMoviedbHomeBinding.inflate(layoutInflater, container, false)
        bindingView.lifecycleOwner = this
        bindingView.viewModel = moviesViewModel
        (requireActivity() as MainActivity).supportActionBar!!.hide()
        sharedPreferences = SharedPreferencesHelper(requireContext())

        if (sharedPreferences.get(SharedPreferencesKey.FIRST_TIME, false)!!) {
            moviesViewModel.offlineMovieList.observe(viewLifecycleOwner, Observer {
                adapter.submitList(it)
            })
        } else {
            moviesViewModel.fetchMovieList()
            sharedPreferences.put(SharedPreferencesKey.FIRST_TIME, true)
        }

        bindingView.apply {
            listMovies.setHasFixedSize(true)
            listMovies.itemAnimator = DefaultItemAnimator()
            listMovies.setLayoutManager(GridLayoutManager(activity, 3))
            listMovies.adapter = adapter
        }

        moviesViewModel.movieList.observe(viewLifecycleOwner, Observer { movieList ->
            adapter.submitList(movieList)
        })


        return bindingView.root
    }

    override fun onClick(clickedView: View) {
        when (clickedView.id) {

        }
    }
}
