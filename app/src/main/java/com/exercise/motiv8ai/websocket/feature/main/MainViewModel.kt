package com.exercise.motiv8ai.websocket.feature.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.exercise.motiv8ai.websocket.data.RemoteRepository
import com.exercise.motiv8ai.websocket.data.entity.Grocery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import okhttp3.internal.wait

class MainViewModel @ViewModelInject constructor(
    private val repository: RemoteRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    lateinit var groceries: LiveData<List<Grocery>>

    var query: MutableLiveData<String> = MutableLiveData()

    fun fetchGroceries() {
        viewModelScope.launch {
            repository.fetchGroceries()
        }
    }

    fun stopFetchingGroceries() {
        repository.stopWebSocket()
    }

    fun setSearchQuery(query: String) {
        this.query.value = query
    }

    fun getGroceriesStream(): LiveData<List<Grocery>> {
        val flow = repository.getGroceries()
        query.value?.let { query ->
            flow.map {
                it.filter { item ->
                    if (query.isNotEmpty()) {
                        return@filter item.weight.contains(query) || item.name.contains(query)
                    } else false
                }
            }
        }
        groceries = flow.flowOn(Dispatchers.IO).asLiveData()
        return groceries
    }
}
