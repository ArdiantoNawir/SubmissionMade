package com.capstone.moviecatalogue.core.utils

import com.capstone.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.capstone.moviecatalogue.core.data.source.remote.response.MovieResponse
import com.capstone.moviecatalogue.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movies = MovieEntity(
                movieId = it.moviesId,
                title = it.title,
                overview = it.overview,
                imagePath = it.imagePath,
                isFavorite = false
            )
            movieList.add(movies)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.movieId,
                title = it.title,
                overview = it.overview,
                imagePath = it.imagePath,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) =
        MovieEntity(
            movieId = input.id,
            title = input.title,
            overview = input.overview,
            imagePath = input.imagePath,
            isFavorite = input.isFavorite
        )
}