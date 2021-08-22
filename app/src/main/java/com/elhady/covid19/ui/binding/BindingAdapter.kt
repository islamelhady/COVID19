package com.elhady.covid19.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import com.elhady.covid19.R


@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String?) {
    view.load(url) {
        error(R.drawable.ic_broken_image)
    }
}