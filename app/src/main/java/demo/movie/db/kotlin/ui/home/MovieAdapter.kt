package demo.movie.db.kotlin.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import demo.movie.db.kotlin.R
import demo.movie.db.kotlin.databinding.ItemMovieCardBinding
import demo.movie.db.kotlin.ui.home.model.Movie

class MovieAdapter : ListAdapter<Movie, MovieAdapter.MovieListViewHolder>(MovieDiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val mContext = parent.context
        val inflater = LayoutInflater.from(mContext)
        val bindingView = DataBindingUtil.inflate<ItemMovieCardBinding>(inflater,
            R.layout.item_movie_card, parent, false)
        return MovieListViewHolder(bindingView)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.dataBind(getItem(position))
    }

    inner class MovieListViewHolder(private val itemMovieCardBinding: ItemMovieCardBinding)
        : RecyclerView.ViewHolder(itemMovieCardBinding.root) {

        fun dataBind(movie: Movie) {
            itemMovieCardBinding.movie = movie

            if(movie.posterPath != null) {
                Glide.with(itemMovieCardBinding.moviePoster)
                    .load("https://image.tmdb.org/t/p/w780" + movie.posterPath)
                    .apply(RequestOptions.bitmapTransform(CenterInside()))
                    .into(itemMovieCardBinding.moviePoster)
            } else {
                itemMovieCardBinding.moviePoster.background = itemMovieCardBinding.moviePoster.context?.let{
                    ContextCompat.getDrawable(itemMovieCardBinding.moviePoster.context, R.drawable.ic_placeholder)
                }
            }
        }
    }

    companion object {
        private val MovieDiffCallback: DiffUtil.ItemCallback<Movie> = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == oldItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}
