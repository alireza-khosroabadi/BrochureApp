package com.alireza.brochure.brochureDetail.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alireza.brochure.domain.repository.BrochureRepository
import com.alireza.brochure.model.brochureDetail.BrochureDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrochureDetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle, private val brochureRepository: BrochureRepository):
    ViewModel() {
    val brochureId = savedStateHandle.get<String>("brochureId")

    private val _uiState = MutableStateFlow<BrochureDetail?>(null)
    val uiState = _uiState.asStateFlow()


    fun loadBrochure(){
        viewModelScope.launch {
            brochureId?.let {
                val brochure =  brochureRepository.findBrochureById(brochureId)
                _uiState.value = brochure
            }
        }
    }
}