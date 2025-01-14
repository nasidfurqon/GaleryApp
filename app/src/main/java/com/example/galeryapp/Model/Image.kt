package com.example.galeryapp.Model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.galeryapp.R

data class Image(@StringRes val place: Int,
    @StringRes val name: Int,
    @StringRes val year: Int,
    @DrawableRes val imageId: Int)

val listOfImage  = mutableListOf(
    Image(R.string.place1, R.string.name1, R.string.year1, R.drawable.place1),
    Image(R.string.place2, R.string.name2, R.string.year2, R.drawable.place2),
    Image(R.string.place3, R.string.name3, R.string.year3, R.drawable.place3),
)
