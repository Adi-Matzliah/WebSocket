package com.exercise.motiv8ai.websocket.feature.movie

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.exercise.motiv8ai.websocket.data.RemoteRepository
import com.exercise.motiv8ai.websocket.data.entity.Grocery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import okhttp3.internal.wait

class MainViewModel @ViewModelInject constructor(
    private val repository: RemoteRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val grocery: LiveData<Grocery>
        get() = repository.getGroceries().flowOn(
            Dispatchers.IO).asLiveData()

    fun fetchGroceries() {
        viewModelScope.launch {
            repository.fetchGroceries()/*.collect {
                val grocery = it
            }*/
        }

    }

}