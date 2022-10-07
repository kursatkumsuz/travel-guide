package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.tripscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase.TripUseCase
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Resource
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripViewModel @Inject constructor(
    private val useCase: TripUseCase
) : ViewModel() {

    private var _bookmarkList = MutableLiveData<Resource<List<TravelModel>>>()
    val bookmarkList: LiveData<Resource<List<TravelModel>>>
        get() = _bookmarkList

    private var _updateStatus = MutableLiveData<Response>()
    val updateStatus: LiveData<Response>
        get() = _updateStatus

    fun loadBookMarkList() {
        viewModelScope.launch {
            _bookmarkList.postValue(useCase.getBookMarkList())
        }
    }

    fun updateData(id: String, isBookmark: Boolean) {
        _updateStatus.value = Response.loading()
        viewModelScope.launch {
            _updateStatus.value =  useCase.updateData(id, isBookmark)
            loadBookMarkList()
        }
    }

}