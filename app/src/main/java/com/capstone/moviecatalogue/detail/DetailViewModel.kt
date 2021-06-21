package com.capstone.moviecatalogue.detail

import androidx.lifecycle.ViewModel
import com.capstone.moviecatalogue.core.domain.model.Movie
import com.capstone.moviecatalogue.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase): ViewModel() {

    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}