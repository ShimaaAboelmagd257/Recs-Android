package com.example.recs.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetailsResponse(
    val adult: Boolean = false,
    val backdropPath: String? = null,
    val belongsToCollection: CollectionResponse? = null,
    val budget: Int = 0,
    val genres: List<GenreDto>? = null,
    val homepage: String? = null,
    val id: Int = 0,
    val imdbId: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double = 0.0,
    val poster_path: String,
    val productionCompanies: List<ProductionCompanyDto>? = null,
    val productionCountries: List<ProductionCountryDto>? = null,
    val releaseDate: String? = null,
    val revenue: Long = 0,
    val runtime: Int = 0,
    val spokenLanguages: List<SpokenLanguageDto>? = null,
    val status: String? = null,
    val tagline: String? = null,
    val title: String,
    val video: Boolean = false,
    val vote_average: Double,
    val voteCount: Int = 0
) : Parcelable


@Parcelize
data class GenreDto(
        val id: Int = 0,
        val name: String? = null
    ) : Parcelable

@Parcelize
    data class ProductionCompanyDto(
        val id: Int = 0,
        val logoPath: String? = null,
        val name: String? = null,
        val originCountry: String? = null
    ) : Parcelable

@Parcelize

    data class ProductionCountryDto(
        val iso31661: String? = null,
        val name: String? = null
    ) : Parcelable

@Parcelize

    data class SpokenLanguageDto(
        val englishName: String? = null,
        val iso6391: String? = null,
        val name: String? = null
    ) : Parcelable

@Parcelize

    data class CollectionResponse(
        val id: Int = 0,
        val name: String? = null,
        val posterPath: String? = null,
        val backdropPath: String? = null
    ) : Parcelable

