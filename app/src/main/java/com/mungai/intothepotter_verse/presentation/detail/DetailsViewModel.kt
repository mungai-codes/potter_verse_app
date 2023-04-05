package com.mungai.intothepotter_verse.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mungai.intothepotter_verse.common.Resource
import com.mungai.intothepotter_verse.di.IoDispatcher
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
class DetailsViewModel @Inject constructor(
    private val repository: PotterVerseRepository,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterDetailsState())
    val state = _state.asStateFlow()

    init {
        getCharacter()
    }

    private fun getCharacter() {
        viewModelScope.launch(ioDispatcher) {
            savedStateHandle.get<String>("characterId")?.let { characterId ->
                if (characterId != "") {
                    repository.getCharacterById(characterId).onEach { result ->
                        when (result) {

                            is Resource.Loading -> {
                                _state.update { it.copy(loading = true) }
                            }

                            is Resource.Success -> {
                                _state.update { it.copy(loading = false, character = result.data) }
                            }

                            is Resource.Error -> {
                                _state.update { it.copy(loading = false, error = result.message) }
                            }
                        }
                    }.launchIn(this)
                }
            }
        }
    }

}
