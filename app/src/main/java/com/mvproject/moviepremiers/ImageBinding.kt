package com.mvproject.moviepremiers

import android.widget.ImageView
import com.squareup.picasso.Picasso
import androidx.databinding.BindingAdapter


@BindingAdapter("app:url")
fun loadImage(view: ImageView, url: String) {
    Picasso.get().load(url).into(view)
}