package com.capstone.moviecatalogue.di

import com.capstone.moviecatalogue.core.domain.usecase.MovieInteractor
import com.capstone.moviecatalogue.core.domain.usecase.MovieUseCase
import com.capstone.moviecatalogue.detail.DetailViewModel
import com.capstone.moviecatalogue.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}

