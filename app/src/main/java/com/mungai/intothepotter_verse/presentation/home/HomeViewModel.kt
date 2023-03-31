package com.mungai.intothepotter_verse.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mungai.intothepotter_verse.common.IoDispatcher
import com.mungai.intothepotter_verse.common.Resource
import com.mungai.intothepotter_verse.domain.repository.PotterVerseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PotterVerseRepository,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    init {
        getAllCharacters()
    }

    fun updateQuery(input: String) {
        _state.update { it.copy(query = input) }
    }

    private fun getAllCharacters() {
        viewModelScope.launch(ioDispatcher) {
            repository.getAllCharacters().onEach { result ->
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
                        _state.update { it.copy(loading = false, errorMessage = result.message) }
                    }
                }
            }.launchIn(this)
        }
    }
}