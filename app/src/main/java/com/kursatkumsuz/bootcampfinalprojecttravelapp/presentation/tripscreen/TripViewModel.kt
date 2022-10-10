package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.tripscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TripEntity
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase.TripUseCase
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Resource
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

    private var _tripList = MutableLiveData<List<TripEntity>>()
    val tripList: LiveData<List<TripEntity>>
        get() = _tripList

    private var _updateStatus = MutableLiveData<Resource<TravelModel>>()
    val updateStatus: LiveData<Resource<TravelModel>>
        get() = _updateStatus

    fun loadBookMarkList() {
        viewModelScope.launch {
            _bookmarkList.postValue(useCase.getBookMarkList())
        }
    }

    fun loadTripList() {
        viewModelScope.launch {
            _tripList.value = useCase.getTripList()
        }
    }

    fun updateData(id: String, isBookmark: Boolean) {
        _updateStatus.value = Resource.loading(null)
        viewModelScope.launch {
            _updateStatus.value = useCase.updateData(id, isBookmark)
            _updateStatus.value = Resource.success(null)
            loadBookMarkList()
        }
    }

    fun deleteTrip(trip: TripEntity) {
        viewModelScope.launch {
            useCase.deleteTrip(trip)
            loadTripList()
        }
    }

}