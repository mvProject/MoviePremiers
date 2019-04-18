package com.mvproject.moviepremiers.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mvproject.moviepremiers.data.model.Movie
import com.mvproject.moviepremiers.R
import com.mvproject.moviepremiers.databinding.MovieItemBinding

class MovieAdapter(var movies: MutableList<Movie>, var context: Context) : RecyclerView.Adapter<MovieHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        //val inflater = LayoutInflater.from(parent.context)
       // val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
       // val binding = MovieItemBinding.inflate(inflater,R.layout.movie_item, parent, false)
        return MovieHolder(parent)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bindItem(movies[position])
    }
}
