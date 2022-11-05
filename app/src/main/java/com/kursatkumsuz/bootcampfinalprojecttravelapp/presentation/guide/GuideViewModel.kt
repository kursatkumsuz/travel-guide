package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.guide

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
class GuideViewModel @Inject constructor(
    private val getTravelByCategoryUseCase: GetTravelByCategoryUseCase,
    private val updateBookmarkUseCase: UpdateBookmarkUseCase,
) : ViewModel() {

    private var _mightNeedList = MutableLiveData<Resource<List<TravelModel>>>()
    val mightNeedList: LiveData<Resource<List<TravelModel>>>
        get() = _mightNeedList

    private var _topPickList = MutableLiveData<Resource<List<TravelModel>>>()
    val topPickList: LiveData<Resource<List<TravelModel>>>
        get() = _topPickList


    fun loadByCategories() {
        viewModelScope.launch {
            _mightNeedList.value = getTravelByCategoryUseCase.getTravelByCategory("mightneed")
            _topPickList.value = getTravelByCategoryUseCase.getTravelByCategory("toppick")
        }
    }

    fun updateData(id: String, isBookmark: Boolean) {
        viewModelScope.launch {
            if (id.isNotEmpty()) {
                updateBookmarkUseCase.updateBookmark(id, isBookmark)
            }
        }
    }

}