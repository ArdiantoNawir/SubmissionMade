package com.capstone.moviecatalogue.core.data.source.remote

import android.util.Log
import com.capstone.moviecatalogue.core.data.source.remote.network.ApiResponse
import com.capstone.moviecatalogue.core.data.source.remote.network.ApiService
import com.capstone.moviecatalogue.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService){
    companion object {

        private const val TAG = "REMOTE DATA SOURCE"

    }

    fun getAllTourism(): Flow<ApiResponse<List<MovieResponse>>> {

        return flow {
            try {
                val response = apiService.getMovies()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)

    }
}