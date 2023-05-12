package com.example.saidur.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.saidur.BuildConfig
import com.example.saidur.R
import com.example.saidur.data.model.Weather
import com.example.saidur.databinding.ItemMovieCardBinding

class MovieAdapter : ListAdapter<Weather, MovieAdapter.MovieListViewHolder>(MovieDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val mContext = parent.context
        val inflater = LayoutInflater.from(mContext)
        val bindingView = DataBindingUtil.inflate<ItemMovieCardBinding>(
            inflater,
            R.layout.item_movie_card, parent, false
        )
        return MovieListViewHolder(bindingView)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.dataBind(getItem(position))
    }

    inner class MovieListViewHolder(private val itemMovieCardBinding: ItemMovieCardBinding) :
        RecyclerView.ViewHolder(itemMovieCardBinding.root) {

        fun dataBind(movie: Weather) {
            itemMovieCardBinding.movie = movie

            if (movie.posterPath != null) {
                Glide.with(itemMovieCardBinding.moviePoster)
                    .load(BuildConfig.IMAGE_URL + movie.posterPath)
                    .apply(RequestOptions.bitmapTransform(CenterInside()))
                    .into(itemMovieCardBinding.moviePoster)
            } else {
                itemMovieCardBinding.moviePoster.background =
                    itemMovieCardBinding.moviePoster.context?.let {
                        ContextCompat.getDrawable(
                            itemMovieCardBinding.moviePoster.context,
                            R.drawable.ic_placeholder
                        )
                    }
            }

            itemMovieCardBinding.movieLayout.setOnClickListener {
                val bundle = bundleOf(
                    "movie_backdropPath" to movie.backdropPath,
                    "movie_overview" to movie.overview
                )
                itemView.findNavController().navigate(R.id.movie_details, bundle)
            }
        }
    }

    companion object {
        private val MovieDiffCallback: DiffUtil.ItemCallback<Weather> =
            object : DiffUtil.ItemCallback<Weather>() {
                override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
                    return oldItem.id == oldItem.id
                }

                override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
                    return oldItem == newItem
                }
            }
    }
}
