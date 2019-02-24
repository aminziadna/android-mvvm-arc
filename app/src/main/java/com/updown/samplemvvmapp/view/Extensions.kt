package com.updown.samplemvvmapp.view

import android.databinding.BindingAdapter
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

@BindingAdapter("bind:visibleOrGone")
fun View.setVisibleOrGone(show: Boolean) {
    visibility = if (show) VISIBLE else GONE
}
