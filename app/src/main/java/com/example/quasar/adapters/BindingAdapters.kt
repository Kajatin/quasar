package com.example.quasar.adapters

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.time.Month
import java.time.format.TextStyle
import java.util.*

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .error(ColorDrawable(Color.BLACK))
            .into(view)
    }
}

@BindingAdapter("dateFormatted")
fun bindDateFormatted(view: TextView, date: String?) {
    if (!date.isNullOrEmpty()) {
        // Extract year
        val year = date.substring(0, 4)
        // Convert the month to text
        val monthNumber = date.substring(4, 6).toInt()
        val month = Month.of(monthNumber).getDisplayName(TextStyle.FULL, Locale.US)
        // Extract day
        val day = date.substring(6, date.length)

        // Combine date values and set to view's text field
        view.text = "$day $month $year"
    }
}