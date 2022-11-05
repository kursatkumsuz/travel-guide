package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.addtrip

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TripEntity
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase.AddTripUseCase
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Constants.Companion.DEFAULT_IMAGE_URL
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTripViewModel @Inject constructor(
    private val useCase: AddTripUseCase
) : ViewModel() {

    private val _selectedImageUrl = MutableLiveData<String>()
    val selectedImageUrl: LiveData<String>
        get() = _selectedImageUrl

    private var _createTripStatusMessage = MutableLiveData<Resource<TripEntity>>()
    val createTripStatusMessage: LiveData<Resource<TripEntity>>
        get() = _createTripStatusMessage

    /**
     * Sets value [_selectedImageUrl]
     * @param [url] for passed to [_selectedImageUrl]
     */
    fun setSelectedImageUrl(url: String) {
        _selectedImageUrl.postValue(url)
    }

    /**
     * If all conditions are right, saves data
     */
    fun createTrip(title: String, country: String, city: String, startDate: String, endDate : String, totalDay : Int) {
        if (title.isEmpty()) {
            _createTripStatusMessage.value = Resource.error("Please enter title", null)
        } else if (country.isEmpty()) {
            _createTripStatusMessage.value = Resource.error("Please enter country", null)
        } else if (city.isEmpty()) {
            _createTripStatusMessage.value = Resource.error("Please enter city", null)
        } else if (startDate.isEmpty()) {
            _createTripStatusMessage.value = Resource.error("Please select date", null)
        } else {
            _createTripStatusMessage.value =  Resource.loading(null)
            viewModelScope.launch {
                useCase.createTrip(title, country, city, startDate, endDate, totalDay, _selectedImageUrl.value ?: DEFAULT_IMAGE_URL)
                _createTripStatusMessage.value =  Resource.success(null)
            }
        }
    }

    /**
     * Resets [_createTripStatusMessage]
     */
    fun resetStatusMessage() {
        _createTripStatusMessage = MutableLiveData<Resource<TripEntity>>()
    }
}