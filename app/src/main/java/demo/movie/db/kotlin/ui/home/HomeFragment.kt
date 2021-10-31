package demo.movie.db.kotlin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import demo.movie.db.kotlin.MainActivity
import demo.movie.db.kotlin.database.DatabaseSingleton
import demo.movie.db.kotlin.databinding.FragmentMoviedbHomeBinding
import demo.movie.db.kotlin.network.MovieEndPoint
import demo.movie.db.kotlin.network.RestServiceGenerator
import demo.movie.db.kotlin.ui.home.model.Movie
import demo.movie.db.kotlin.utils.SharedPreferencesHelper
import demo.movie.db.kotlin.utils.SharedPreferencesKey
import java.util.*

class HomeFragment : Fragment(), View.OnClickListener {
    private lateinit var bindingView: FragmentMoviedbHomeBinding
    private val adapter = MovieAdapter()
    private lateinit var sharedPreferences: SharedPreferencesHelper
    private var packageList: List<Movie> = ArrayList<Movie>()

    private val viewModel: HomeListViewModel by lazy {
        val endPoint = RestServiceGenerator.createService(MovieEndPoint::class.java)
        val sharedPreferencesHelper = SharedPreferencesHelper(requireContext())
        HomeListVMFactory(HomeListRepo(endPoint), sharedPreferencesHelper, requireContext()).create(HomeListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        bindingView = FragmentMoviedbHomeBinding.inflate(layoutInflater, container, false)
        bindingView.viewModel = viewModel
        (requireActivity() as MainActivity).supportActionBar!!.hide()
        sharedPreferences = SharedPreferencesHelper(requireContext())

        if(sharedPreferences.get(SharedPreferencesKey.FIRST_TIME, false)!!) {
            packageList = DatabaseSingleton.getDatabase(activity)!!.movieLocalDataDAO()!!.getList() as List<Movie>
            adapter.submitList(packageList)
        } else {
            viewModel.fetchMovieList()
            sharedPreferences.put(SharedPreferencesKey.FIRST_TIME, true)
        }

        bindingView.apply {
            listMovies.setHasFixedSize(true)
            listMovies.itemAnimator = DefaultItemAnimator()
            listMovies.setLayoutManager(GridLayoutManager(activity, 3))
            listMovies.adapter = adapter
        }

        viewModel.showMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.movieList.observe(viewLifecycleOwner, Observer { movieList ->
            adapter.submitList(movieList)
            DatabaseSingleton.getDatabase(context)?.movieLocalDataDAO()?.addAll(movieList)
        })

        return bindingView.root
    }

    override fun onClick(clickedView: View) {
        when(clickedView.id) {

        }
    }
}
