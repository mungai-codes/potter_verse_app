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
        getMainCast()
        getStaff()
        getSpells()
        getStudents()
    }

    fun updateQuery(input: String) {
        _state.update { it.copy(query = input) }
    }

    private fun getMainCast() {
        viewModelScope.launch(ioDispatcher) {
            repository.getMainCast().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(loading = true) }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                mainCast = result.data ?: emptyList()
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

    private fun getStaff() {
        viewModelScope.launch(ioDispatcher) {
            repository.getStaff().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(loadingStaff = true) }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                loadingStaff = false,
                                staff = result.data ?: emptyList()
                            )
                        }
                    }

                    is Resource.Error -> {
                        _state.update { it.copy(loadingStaff = false, staffError = result.message) }

                    }
                }
            }.launchIn(this)
        }
    }

    private fun getSpells() {
        viewModelScope.launch(ioDispatcher) {
            repository.getSpells().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(loading = true) }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                spells = result.data?.shuffled()?.take(15) ?: emptyList()
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

    private fun getStudents() {
        viewModelScope.launch(ioDispatcher) {
            repository.getStudents().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(loading = true) }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                students = result.data ?: emptyList()
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

    fun getSpellById(id: String) {
        viewModelScope.launch(ioDispatcher) {
            repository.getSpellById(id = id).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(loading = true) }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                spell = result.data
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