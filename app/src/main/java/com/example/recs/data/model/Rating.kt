package com.example.recs.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rating (
    val userId:Long,
    val movieId:Int,
    val rating:Double
): Parcelable