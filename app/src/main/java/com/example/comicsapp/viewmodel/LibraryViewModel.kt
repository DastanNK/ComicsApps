package com.example.comicsapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comicsapp.Api.ApiRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val repo:ApiRepo
): ViewModel() {
    val result=repo.characters
    val queryText= MutableStateFlow("")
    private val queryInput= Channel<String>(Channel.CONFLATED)

    init{
        retrieveCharacters()
    }
    fun retrieveCharacters(){
        viewModelScope.launch (Dispatchers.IO) {
            queryInput.receiveAsFlow()
                .filter { validateQuery(it) }
                .debounce(1000)
                .collect{repo.query(it)}
        }
    }
    fun validateQuery(query: String): Boolean=query.length >= 2

    fun onQueryChanged(query: String){
        queryText.value = query
        queryInput.trySend(query)
    }
}