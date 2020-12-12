package com.exercise.motiv8ai.websocket.feature.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.exercise.motiv8ai.websocket.R
import com.exercise.motiv8ai.websocket.feature.movie.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()

    private val adapter = GroceriesRecyclerViewAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchGroceries()
        rv_groceries.adapter = adapter
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.grocery.observe(this) {
            Timber.d("Grocery: $it" )
            adapter.addItemAtTop(it)
            rv_groceries.smoothScrollToPosition(0)
        }
    }
}