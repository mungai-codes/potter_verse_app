package com.mungai.intothepotter_verse.presentation.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mungai.intothepotter_verse.common.Category
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
class SearchViewModel @Inject constructor(
    private val repository: PotterVerseRepository,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(SearchScreenState())
    val state = _state.asStateFlow()


    init {
        loadData()
    }

    fun loadData() {
        savedStateHandle.get<String>("category")?.let { category ->

                when (category) {
                    Category.Cast.category -> {
                        _state.update { it.copy(category = Category.Cast.category) }
                        getAllCast()
                    }

                    Category.Spell.category -> {
                        //TODO
                        _state.update { it.copy(category = Category.Spell.category) }
                    }

                    Category.Wizard.category -> {
                        //TODO
                        _state.update { it.copy(category = Category.Wizard.category) }
                    }
                }
        }

    }


    fun getAllCast() {
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
                        _state.update {
                            it.copy(
                                loading = false,
                                error = result.message
                            )
                        }
                    }
                }
            }.launchIn(this)
        }
    }
}