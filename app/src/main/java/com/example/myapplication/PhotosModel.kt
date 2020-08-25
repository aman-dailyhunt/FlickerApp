package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PhotosViewModel : ViewModel() {
    private val mutablePhotosLiveData = MutableLiveData<MutableList<Photo>>()
    val photosLiveData: LiveData<MutableList<Photo>> = mutablePhotosLiveData

    fun fetchImages(search : String, page : Int) {
        viewModelScope.launch {
            val searchResponse = WebClient.client.fetchImages(search,page)
            val photosList = searchResponse.photos.photo.map { photo ->
                    Photo(
                        id = photo.id,
                        url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                        title = photo.title

                    )
            }
            val list = mutableListOf<Photo>()
            if (mutablePhotosLiveData.value != null) {
                list.addAll(mutablePhotosLiveData.value!!)
            }
            list.addAll(photosList)
            mutablePhotosLiveData.value = list
        }
    }
}