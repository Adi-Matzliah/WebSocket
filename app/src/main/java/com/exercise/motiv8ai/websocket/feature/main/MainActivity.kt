package com.exercise.motiv8ai.websocket.feature.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.widget.addTextChangedListener
import com.exercise.motiv8ai.websocket.R
import com.exercise.motiv8ai.websocket.data.entity.Grocery
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main),
    SearchView.OnQueryTextListener {

    private val viewModel: MainViewModel by viewModels()

    private val adapter = GroceriesRecyclerViewAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rv_groceries.adapter = adapter
        setListeners()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.getGroceriesStream().observe(this) {
            Timber.d("Grocery: $it" )
            showGroceryList(it)
        }
    }

    private fun setListeners() {
        sv_filter.setOnQueryTextListener(this)
    }

    private fun showGroceryList(groceries: List<Grocery>) {
        rv_groceries.smoothScrollToPosition(0)
        adapter.addItems(groceries)
    }

    fun onStop(view: View) {
        viewModel.stopFetchingGroceries()
    }

    fun onStart(view: View) =
        viewModel.fetchGroceries()

    override fun onQueryTextSubmit(query: String?) = true

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let {
            viewModel.setSearchQuery(it)
        }
        return true
    }
}