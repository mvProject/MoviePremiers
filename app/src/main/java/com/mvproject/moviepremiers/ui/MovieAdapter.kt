package com.mvproject.moviepremiers.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvproject.moviepremiers.data.model.Movie

class MovieAdapter(var movies: MutableList<Movie>) : RecyclerView.Adapter<MovieHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(parent)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bindItem(movies[position])
    }
}
