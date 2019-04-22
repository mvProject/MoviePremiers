package com.mvproject.moviepremiers.bindings

import androidx.databinding.BindingAdapter
import com.github.loadingview.LoadingView

@BindingAdapter("loading")
fun showLoadingDialog(view: LoadingView, show: Boolean) {
    if (show) view.start() else view.stop()
}