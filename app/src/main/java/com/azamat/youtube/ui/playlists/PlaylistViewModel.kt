package com.azamat.youtube.ui.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.azamat.youtube.models.resource_courutines.Resource
import com.azamat.youtube.models.youtube.PlaylistResponse
import com.azamat.youtube.repository.NetworkRepository

class PlaylistViewModel : ViewModel() {

    var repositor = NetworkRepository()

    fun fetchPlaylistFromServer(): LiveData<Resource<PlaylistResponse>> {
        return repositor.fetchPlaylists()
    }

    fun fetchNextPlaylist(nextPageToken: String): LiveData<Resource<PlaylistResponse>> {
        return repositor.fetchNextPlaylist(nextPageToken)
    }

}