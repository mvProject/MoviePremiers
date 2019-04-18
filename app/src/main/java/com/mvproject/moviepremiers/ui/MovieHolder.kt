package com.mvproject.moviepremiers.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mvproject.moviepremiers.R
import com.mvproject.moviepremiers.data.model.Movie
import com.mvproject.moviepremiers.databinding.MovieItemBinding
import com.mvproject.moviepremiers.bindings.BindingHelper

class MovieHolder(
    private val parent: ViewGroup,
    private val binding: MovieItemBinding = DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.movie_item,
        parent,
        false
    )
) : RecyclerView.ViewHolder(binding.root) {

    fun bindItem(movie: Movie) {
        binding.movie = movie
        binding.clickListener = BindingHelper()
    }


}