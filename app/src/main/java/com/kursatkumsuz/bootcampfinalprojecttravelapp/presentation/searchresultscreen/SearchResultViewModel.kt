package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchresultscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase.SearchResultUseCase
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val useCase: SearchResultUseCase
) : ViewModel() {

    private var _searchResultList = MutableLiveData<Resource<List<TravelModel>>>()
    val searchResultList: LiveData<Resource<List<TravelModel>>>
        get() = _searchResultList

    init {

        loadSearchResult()
    }

    private fun loadSearchResult() {
        viewModelScope.launch {
            _searchResultList.value = useCase.getSearchResult()

        }
    }

}