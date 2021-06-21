package com.capstone.moviecatalogue.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.moviecatalogue.core.ui.MovieAdapter
import com.capstone.moviecatalogue.detail.DetailActivity
import com.capstone.moviecatalogue.favorite.databinding.ActivityFavoriteBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var favoriteBinding: ActivityFavoriteBinding

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(favoriteBinding.root)

        supportActionBar?.title = "Movie Favorite"

        loadKoinModules(favoriteModule)

        val favAdapter = MovieAdapter()
        favAdapter.onItemClick = { movie ->
            Intent(this, DetailActivity::class.java).also {
                it.putExtra(DetailActivity.EXTRA_MOVIE, movie)
                startActivity(it)
            }

        }

        favoriteViewModel.favoriteMovie.observe(this, {
            favAdapter.setList(it)
        })

        favoriteBinding.rvMovie.apply {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            setHasFixedSize(true)
            adapter = favAdapter
        }
    }
}