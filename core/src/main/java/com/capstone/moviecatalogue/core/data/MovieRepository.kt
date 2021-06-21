package com.capstone.moviecatalogue.core.data

import com.capstone.moviecatalogue.core.data.source.local.LocalDataSource
import com.capstone.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.capstone.moviecatalogue.core.data.source.remote.network.ApiResponse
import com.capstone.moviecatalogue.core.data.source.remote.response.MovieResponse
import com.capstone.moviecatalogue.core.domain.model.Movie
import com.capstone.moviecatalogue.core.domain.repository.IMovieRepository
import com.capstone.moviecatalogue.core.utils.AppExecutors
import com.capstone.moviecatalogue.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: com.capstone.moviecatalogue.core.data.source.remote.RemoteDataSource,
    private val localDataSource: com.capstone.moviecatalogue.core.data.source.local.LocalDataSource,
    private val appExecutors: AppExecutors
): IMovieRepository {

    override fun getAllMovie(): Flow<com.capstone.moviecatalogue.core.data.Resource<List<Movie>>> =
        object : com.capstone.moviecatalogue.core.data.NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllTourism()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }

        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute{localDataSource.setFavoriteMovie(movieEntity, state)}
    }
}