package com.capstone.moviecatalogue.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.moviecatalogue.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase): ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()
}