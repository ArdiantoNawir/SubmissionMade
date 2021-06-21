package com.capstone.moviecatalogue.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (
    val id: Int,
    val title: String,
    val overview: String,
    val imagePath: String,
    val isFavorite: Boolean

): Parcelable