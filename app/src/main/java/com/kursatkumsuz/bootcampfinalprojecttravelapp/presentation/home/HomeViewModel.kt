package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase.GetAllTravelUseCase
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase.GetTravelByCategoryUseCase
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllTravelUseCase: GetAllTravelUseCase,
    private val getTravelByCategory: GetTravelByCategoryUseCase
) : ViewModel() {

    private var _allDataList = MutableLiveData<Resource<List<TravelModel>>>()
    val allDataList: LiveData<Resource<List<TravelModel>>>
        get() = _allDataList

    private var _listByCategory = MutableLiveData<Resource<List<TravelModel>>>()
    val listByCategory: LiveData<Resource<List<TravelModel>>>
        get() = _listByCategory

    init {
        loadAllList()
    }

    /**
     * Gets all data list
     */
    fun loadAllList() {
        viewModelScope.launch {
            _allDataList.value = getAllTravelUseCase.getAllTravel()
        }
    }

    fun loadByCategories(category : String) {
        viewModelScope.launch {
            _listByCategory.value = getTravelByCategory.getTravelByCategory(category)
        }
    }

}