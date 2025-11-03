package com.example.recs.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TmdbApiResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
) : Parcelable
