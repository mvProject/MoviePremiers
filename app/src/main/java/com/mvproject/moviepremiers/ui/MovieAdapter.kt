package com.mvproject.moviepremiers.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvproject.moviepremiers.data.model.Movie
import com.mvproject.moviepremiers.R

class MovieAdapter(var movies: MutableList<Movie>, var context: Context) : RecyclerView.Adapter<MovieHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bindNote(movies[position],context)
    }
}
