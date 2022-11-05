package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase.GetTravelByCategoryUseCase
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase.UpdateBookmarkUseCase
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getTravelByCategoryUseCase: GetTravelByCategoryUseCase,
    private val updateBookmarkUseCase: UpdateBookmarkUseCase
) : ViewModel() {

    private var _topDestinationList = MutableLiveData<Resource<List<TravelModel>>>()
    val topDestinationList: LiveData<Resource<List<TravelModel>>>
        get() = _topDestinationList

    private var _nearbyList = MutableLiveData<Resource<List<TravelModel>>>()
    val nearbyList: LiveData<Resource<List<TravelModel>>>
        get() = _nearbyList


    /**
     * Gets data by category
     */
    fun loadData() {
        _topDestinationList.value = Resource.loading(null)
        _nearbyList.value = Resource.loading(null)
        viewModelScope.launch {
            _topDestinationList.value =
                getTravelByCategoryUseCase.getTravelByCategory("topdestination")
            _nearbyList.value = getTravelByCategoryUseCase.getTravelByCategory("nearby")
        }
    }

    /**
     * Updates value of isBookmark that given id
     * @param [id] for update data
     * @param [isBookmark] for update data
     */
    fun addBookmark(id: String, isBookmark: Boolean) {
        viewModelScope.launch {
            if (id.isNotEmpty()) {
                updateBookmarkUseCase.updateBookmark(id, isBookmark)
            }
        }
    }
}