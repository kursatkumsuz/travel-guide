package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchimagescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.ImageModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase.SearchImageUseCase
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchImageViewModel @Inject constructor(
    private val useCase: SearchImageUseCase
) : ViewModel() {

    private var _imageList = MutableLiveData<Resource<ImageModel>>()
    val imageList: LiveData<Resource<ImageModel>>
        get() = _imageList

    private var _selectedImageUrl = MutableLiveData<String>()
    val selectedImageUrl: LiveData<String>
        get() = _selectedImageUrl

    fun searchImage(searchString: String) {
        if (searchString.isEmpty()) {
            return
        }
        _imageList.value = Resource.loading(null)
        viewModelScope.launch {
            _imageList.value = useCase.searchImage(searchString)
        }
    }
}