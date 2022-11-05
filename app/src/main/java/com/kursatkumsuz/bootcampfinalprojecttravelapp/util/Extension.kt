package com.kursatkumsuz.bootcampfinalprojecttravelapp.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kursatkumsuz.bootcampfinalprojecttravelapp.R


    fun ImageView.downloadUrl(url: String?) {
        Glide.with(this)
            .setDefaultRequestOptions(
                RequestOptions()
                    .placeholder(R.drawable.pic_glide_default)
                    .error(R.drawable.pic_glide_default)
            )
            .load(url)
            .into(this)
    }
