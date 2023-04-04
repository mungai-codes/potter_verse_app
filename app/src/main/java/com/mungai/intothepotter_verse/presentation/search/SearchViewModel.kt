package com.mungai.intothepotter_verse.presentation.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mungai.intothepotter_verse.common.IoDispatcher
import com.mungai.intothepotter_verse.common.Resource
import com.mungai.intothepotter_verse.domain.repository.PotterVerseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: PotterVerseRepository,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(SearchScreenState())
    val state = _state.asStateFlow()

    private var searchJob: Job? = null

    init {
        savedStateHandle.get<String>("query")?.let { query ->
            searchByName(name = query)
        }
    }

    fun onSearch() {
        if (_state.value.isNormalSearch) {
            searchByName(name = _state.value.query)
        } else {
            searchByNameAndHouse(name = _state.value.query, house = _state.value.house)
        }
    }

    fun searchByName(name: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(ioDispatcher) {
            repository.getCharactersByName(name = name).onEach { result ->

                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(loading = true) }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                characters = result.data ?: emptyList()
                            )
                        }
                    }

                    is Resource.Error -> {
                        _state.update { it.copy(loading = false, error = result.message) }

                    }
                }
            }.launchIn(this)
        }
    }


    fun searchByNameAndHouse(name: String, house: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(ioDispatcher) {
            repository.getCharactersByNameAndHouse(name = name, house = house).onEach { result ->

                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(loading = true) }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                characters = result.data ?: emptyList()
                            )
                        }
                    }

                    is Resource.Error -> {
                        _state.update { it.copy(loading = false, error = result.message) }

                    }
                }
            }.launchIn(this)
        }
    }

    fun updateQuery(input: String) {
        _state.update { it.copy(query = input) }
    }

    fun updateHouseName(input: String) {
        _state.update { it.copy(house = input) }
    }

    fun updateSearchType(input: Boolean) {
        _state.update { it.copy(isNormalSearch = input) }
    }
}