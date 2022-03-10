package com.mvproject.moviepremiers.domain.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mvproject.moviepremiers.databinding.MovieItemBinding
import com.mvproject.moviepremiers.domain.model.Movie
import com.mvproject.moviepremiers.utils.OnClickListener

class MoviesAdapter(private val onClickListener: OnClickListener<Movie>) :
    RecyclerView.Adapter<MoviesAdapter.ItemViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean {
            return oldItem.titleRus == newItem.titleRus
        }

        override fun areContentsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var items: List<Movie>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.apply {
            val item = items[position]
            movieActors.text = item.actors
            movieCountry.text = item.country
            movieDate.text = item.date
            movieDirectors.text = item.directors
            movieGenre.text = item.genre
            //movieActors.text = item.link
            movieNameEng.text = item.titleEng
            movieNameRus.text = item.titleRus

            movieLogo.load(item.image) {
                //crossfade(true)
            }

            movieDate.setOnClickListener {
                onClickListener.onItemClick(item)
            }
        }
    }

    inner class ItemViewHolder(val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
