package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.guidescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.GuideCategoryModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase.GuideUseCase
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuideViewModel @Inject constructor(
    private val useCase: GuideUseCase
) : ViewModel() {


    private var _categoryList = MutableLiveData<Resource<List<GuideCategoryModel>>>()
    val categoryList: LiveData<Resource<List<GuideCategoryModel>>>
        get() = _categoryList

    private var _mightNeedList = MutableLiveData<Resource<List<TravelModel>>>()
    val mightNeedList: LiveData<Resource<List<TravelModel>>>
        get() = _mightNeedList

    private var _topPickList = MutableLiveData<Resource<List<TravelModel>>>()
    val topPickList: LiveData<Resource<List<TravelModel>>>
        get() = _topPickList

    init {
        loadMightNeedList()
        loadTopPickList()
        getCategories()
    }

    private fun loadMightNeedList() {
        _mightNeedList.value = Resource.loading(null)
        viewModelScope.launch {
            _mightNeedList.value = useCase.getMightNeedList()
        }
    }

    private fun loadTopPickList() {
        _mightNeedList.value = Resource.loading(null)
        viewModelScope.launch {
            _topPickList.value = useCase.getTopPickList()
        }
    }

    fun updateData(id: String, isBookmark: Boolean) {
        viewModelScope.launch {
            if (id.isNotEmpty()) {
                useCase.updateData(id, isBookmark)
            }
        }
    }

    fun getCategories() {
        viewModelScope.launch {
            _categoryList.value = useCase.getCategories()
        }
    }

}