package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.homescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase.HomeUseCase
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: HomeUseCase
) : ViewModel() {

    private var _allDataList = MutableLiveData<Resource<List<TravelModel>>>()
    val allDataList: LiveData<Resource<List<TravelModel>>>
        get() = _allDataList

    private var _flightList = MutableLiveData<Resource<List<TravelModel>>>()
    val flightList: LiveData<Resource<List<TravelModel>>>
        get() = _flightList

    private var _hotelList = MutableLiveData<Resource<List<TravelModel>>>()
    val hotelList: LiveData<Resource<List<TravelModel>>>
        get() = _hotelList

    private var _transportationList = MutableLiveData<Resource<List<TravelModel>>>()
    val transportationList: LiveData<Resource<List<TravelModel>>>
        get() = _transportationList

    init {
        loadAllList()
        loadFlightList()
        loadHotelList()
        loadTransportationList()
    }

    private fun loadAllList() {
        viewModelScope.launch {
            _allDataList.value = useCase.getAllList()
        }
    }

    private fun loadFlightList() {
        viewModelScope.launch {
            _flightList.value = useCase.getFlightList()
        }
    }

    private fun loadHotelList() {
        viewModelScope.launch {
            _hotelList.value = useCase.getHotelList()
        }
    }
    private fun loadTransportationList() {
        viewModelScope.launch {
            _transportationList.value = useCase.getTransportationList()
        }
    }
}