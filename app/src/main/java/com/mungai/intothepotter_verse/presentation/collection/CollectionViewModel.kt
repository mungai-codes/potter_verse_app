package com.mungai.intothepotter_verse.presentation.collection

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mungai.intothepotter_verse.common.Collection
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
class CollectionViewModel @Inject constructor(
    private val repository: PotterVerseRepository,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _state = MutableStateFlow(CollectionScreenState())
    val state = _state.asStateFlow()


    init {
        loadData()
    }

    private fun loadData() {
        savedStateHandle.get<String>("collection")?.let { collection ->
            when (collection) {
                Collection.Cast.name -> {
                    _state.update { it.copy(collection = collection, showSpells = false) }
                    getAllCast()
                }
                Collection.Spell.name -> {
                    _state.update { it.copy(collection = collection, showSpells = true) }
                    getSpells()
                }
                Collection.House.name -> {
                    savedStateHandle.get<String>("house")?.let { house ->
                        getHouseMembers(house = house)
                        _state.update { it.copy(house = house) }
                    }
                    _state.update {
                        it.copy(
                            collection = collection,
                            showSpells = false
                        )
                    }
                }
            }
        }

    }


    private fun getAllCast() {
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
                                spells = result.data?.shuffled() ?: emptyList()
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

    private fun getHouseMembers(house: String) {
        viewModelScope.launch(ioDispatcher) {
            repository.getCharactersByHouse(house = house).onEach { result ->
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
}