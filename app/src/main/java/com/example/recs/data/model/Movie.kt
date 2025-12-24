package com.example.recs.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (

     val id :Int,
     val poster_path: String,
     val title: String,
     val vote_average :Double,

) : Parcelable
