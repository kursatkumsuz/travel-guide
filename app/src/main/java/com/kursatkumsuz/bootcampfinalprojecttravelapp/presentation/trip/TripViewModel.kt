package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.trip

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TripEntity
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase.DeleteTripUseCase
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase.GetAllTravelUseCase
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase.GetTripUseCase
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase.UpdateBookmarkUseCase
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TripViewModel @Inject constructor(
    private val getAllTravelUseCase: GetAllTravelUseCase,
    private val getTripUseCase: GetTripUseCase,
    private val deleteTripUseCase: DeleteTripUseCase,
    private val updateBookmarkUseCase: UpdateBookmarkUseCase
) : ViewModel() {

    private var _bookmarkList = MutableLiveData<List<TravelModel>>()
    val bookmarkList: LiveData<List<TravelModel>>
        get() = _bookmarkList

    private var _tripList = MutableLiveData<List<TripEntity>>()
    val tripList: LiveData<List<TripEntity>>
        get() = _tripList

    private var _deleteStatus = MutableLiveData<Resource<TravelModel>>()
    val deleteStatus: LiveData<Resource<TravelModel>>
        get() = _deleteStatus

    fun loadBookMarkList() {
        viewModelScope.launch(Dispatchers.Default) {

            val allData = getAllTravelUseCase.getAllTravel().data

            val result = allData?.filter { it.isBookmark }
            result?.let {
                _bookmarkList.postValue(it)
            }
        }
    }

    fun loadTripList() {
        viewModelScope.launch {
            _tripList.value = getTripUseCase.getTripList()
        }
    }

    fun deleteBookmark(id: String, isBookmark: Boolean) {
        _deleteStatus.value = Resource.loading(null)
        viewModelScope.launch {
            updateBookmarkUseCase.updateBookmark(id, isBookmark)
            _deleteStatus.value = Resource.success(null)
            loadBookMarkList()

        }
    }

    fun deleteTrip(trip: TripEntity) {
        viewModelScope.launch {
            deleteTripUseCase.deleteTrip(trip)
            loadTripList()
        }
    }

}