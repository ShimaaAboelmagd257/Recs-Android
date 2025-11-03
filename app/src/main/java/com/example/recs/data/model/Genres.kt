package com.example.recs.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genres (
    val genres: List<Genre>
) : Parcelable

@Parcelize
data class Genre(

    val id: Int,
    val name:String

) : Parcelable