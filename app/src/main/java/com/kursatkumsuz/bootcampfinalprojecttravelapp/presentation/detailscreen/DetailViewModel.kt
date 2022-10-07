package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.detailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase.DetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: DetailUseCase
) : ViewModel() {

    fun updateData(id: String, isBookmark: Boolean) {
        viewModelScope.launch {
            if (id.isNotEmpty()) {
                useCase.updateData(id, isBookmark)
            }
        }
    }
}