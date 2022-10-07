package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase.SearchUseCase
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: SearchUseCase
) : ViewModel() {

    private var _topDestinationList = MutableLiveData<Resource<List<TravelModel>>>()
    val topDestinationList: LiveData<Resource<List<TravelModel>>>
        get() = _topDestinationList

    private var _nearbyList = MutableLiveData<Resource<List<TravelModel>>>()
    val nearbyList: LiveData<Resource<List<TravelModel>>>
        get() = _nearbyList

    init {
        loadTopDestinationList()
        loadNearbyList()
    }

    private fun loadTopDestinationList() {
        viewModelScope.launch {
            _topDestinationList.value = useCase.getTopList()
        }
    }

    private fun loadNearbyList() {
        viewModelScope.launch {
            _nearbyList.value = useCase.getNearbyList()
        }
    }
}