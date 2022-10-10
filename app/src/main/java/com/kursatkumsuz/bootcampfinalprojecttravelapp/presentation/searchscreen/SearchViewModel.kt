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
    /**
     * Gets data that category is top destination
     */
    private fun loadTopDestinationList() {
        _topDestinationList.value = Resource.loading(null)
        viewModelScope.launch {
            _topDestinationList.value = useCase.getTopList()
        }
    }
    /**
     * Gets data that category is nearby
     */
    private fun loadNearbyList() {
        _nearbyList.value = Resource.loading(null)
        viewModelScope.launch {
            _nearbyList.value = useCase.getNearbyList()
        }
    }

    /**
     * Updates value of isBookmark that given id
     * @param [id] for update data
     * @param [isBookmark] for update data
     */
    fun updateData(id: String, isBookmark: Boolean) {
        viewModelScope.launch {
            if (id.isNotEmpty()) {
                useCase.updateData(id, isBookmark)
            }
        }
    }
}