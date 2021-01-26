package com.azamat.youtube.ui.details

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azamat.youtube.models.resource_courutines.Resource
import com.azamat.youtube.models.youtube.Item
import com.azamat.youtube.models.youtube.PlaylistResponse
import com.azamat.youtube.repository.LocalRepository
import com.azamat.youtube.repository.NetworkRepository

class DetailsViewHolder : ViewModel() {
    var repo = NetworkRepository()
    var localData = MutableLiveData<MutableList<Item>>()
    lateinit var localRepo: LocalRepository

    fun initRepository( context: Context) {
        localRepo = LocalRepository(context)
    }

    fun addDetailsToDB(list: MutableList<Item>) {
        localRepo.addDetailsLocal(list)
    }

    fun fetchDetailsListFromServer(videoId: String): LiveData<Resource<PlaylistResponse>> {
        return repo.fetchDetailsList(videoId)
    }

    fun fetchNextDetailsList(videoId: String, nextPageToken: String): LiveData<Resource<PlaylistResponse>> {
        return repo.fetchNextDetailsList(videoId,nextPageToken)
    }

    fun deleteAll() {
        localRepo.deleteAll()
    }
}