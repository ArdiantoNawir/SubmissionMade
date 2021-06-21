package com.capstone.moviecatalogue.core.data.source.remote.network

import com.capstone.moviecatalogue.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET

interface ApiService {
    @GET("movie/popular?api_key=40004cf20b6c50e10261f76a963f62a2")
    suspend fun getMovies(): ListMovieResponse
}