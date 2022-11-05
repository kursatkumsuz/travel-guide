package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchresult

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase.GetAllTravelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val getAllTravelUseCase: GetAllTravelUseCase
) : ViewModel() {

    private var _searchResultList = MutableLiveData<List<TravelModel>>()
    val searchResultList: LiveData<List<TravelModel>>
        get() = _searchResultList


    fun searchList(query: String) {
        viewModelScope.launch(Dispatchers.Default) {

            val allData = getAllTravelUseCase.getAllTravel().data

            val result = allData?.filter {
                it.title.lowercase(Locale.ROOT).contains(query)
                        || it.description.lowercase(Locale.ROOT).contains(query)
                        || it.country.lowercase(Locale.ROOT).contains(query)
                        || it.city.lowercase(Locale.ROOT).contains(query)
            }
            result?.let {
                _searchResultList.postValue(it)
            }
        }
    }
}