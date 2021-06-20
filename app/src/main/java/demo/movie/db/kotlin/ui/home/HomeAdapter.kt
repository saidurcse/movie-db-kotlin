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
import demo.movie.db.kotlin.ui.home.model.Movie

/*class HomeAdapter : ListAdapter<Movie, HomeAdapter.DoctorListViewHolder>(DoctorDiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorListViewHolder {
        val mContext = parent.context
        val inflater = LayoutInflater.from(mContext)
        val bindingView = DataBindingUtil.inflate<ItemMovieCardBinding>(inflater,
            R.layout.item_movie_card, parent, false)
        return DoctorListViewHolder(bindingView)
    }

    override fun onBindViewHolder(holder: DoctorListViewHolder, position: Int) {
        holder.dataBind(getItem(position))
    }

    inner class DoctorListViewHolder(private val itemDoctorCardBinding: ItemDoctorCardBinding)
        : RecyclerView.ViewHolder(itemDoctorCardBinding.root) {

        fun dataBind(doctor: Doctor) {
            itemDoctorCardBinding.doctor = doctor

            if(doctor.imageUrl != null) {
                Glide.with(itemDoctorCardBinding.icDoctor)
                    .load(doctor.imageUrl)
                    .apply(RequestOptions.bitmapTransform(CenterInside()))
                    .into(itemDoctorCardBinding.icDoctor)
            } else {
                itemDoctorCardBinding.icDoctor.background = itemDoctorCardBinding.icDoctor.context?.let{
                    ContextCompat.getDrawable(itemDoctorCardBinding.icDoctor.context, R.drawable.demo_avatar)
                }
            }
        }
    }

    companion object {
        private val DoctorDiffCallback: DiffUtil.ItemCallback<Doctor> = object : DiffUtil.ItemCallback<Doctor>() {
            override fun areItemsTheSame(oldItem: Doctor, newItem: Doctor): Boolean {
                return oldItem.imageUrl == oldItem.imageUrl
            }

            override fun areContentsTheSame(oldItem: Doctor, newItem: Doctor): Boolean {
                return oldItem == newItem
            }
        }
    }
}*/
