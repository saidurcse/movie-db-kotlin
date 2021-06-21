package demo.movie.db.kotlin.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import demo.movie.db.kotlin.R
import demo.movie.db.kotlin.databinding.FragmentMoviedbDetailsBinding

class MovieDetailsFragment : Fragment() {
    private lateinit var bindingView: FragmentMoviedbDetailsBinding
    private var movie_backdropPath: String? = null
    private var movie_overview: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        bindingView = FragmentMoviedbDetailsBinding.inflate(layoutInflater, container, false)
        movie_backdropPath = arguments?.getString("movie_backdropPath")
        movie_overview = arguments?.getString("movie_overview")

        if(movie_backdropPath != null) {
                Glide.with(bindingView.moviePoster)
                    .load("https://image.tmdb.org/t/p/w780" + movie_backdropPath)
                    .apply(RequestOptions.bitmapTransform(CenterInside()))
                    .into(bindingView.moviePoster)
            } else {
            bindingView.moviePoster.background = bindingView.moviePoster.context?.let {
                ContextCompat.getDrawable(
                    bindingView.moviePoster.context,
                    R.drawable.ic_placeholder
                ) }
            }

        if(movie_overview != null) {
            bindingView.movieOverview.text = movie_overview
        }

        return bindingView.root
    }
}
