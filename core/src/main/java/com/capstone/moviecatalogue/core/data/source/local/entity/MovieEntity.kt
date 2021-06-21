package com.capstone.moviecatalogue.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity (

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var movieId: Int,

    @ColumnInfo(name ="title")
    var title: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "poster_path")
    var imagePath: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false

)