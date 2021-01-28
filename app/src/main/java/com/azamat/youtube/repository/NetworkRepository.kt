package com.azamat.youtube.repository


import android.content.Context
import androidx.lifecycle.liveData
import com.azamat.youtube.data.local.room.YouTubeDataBase
import com.azamat.youtube.data.network.retrofit.RetrofitClient
import com.azamat.youtube.models.resource_courutines.Resource
import com.azamat.youtube.models.youtube.Item
import com.azamat.youtube.utils.Constants.Companion.API
import kotlinx.coroutines.Dispatchers

class NetworkRepository {

    val channelId = "UCVVAnxQ2YMC_qlc7QfPA2YQ"
    val key  = API
    val part = "snippet,contentDetails"
    val maxResults = 10
    private val api = RetrofitClient().retrofitInstance()


    fun fetchPlaylists() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.fetchPlaylists(part,channelId,key,maxResults)))
        } catch (ex: Exception) {
            emit(Resource.error(data = null, message = ex.message.toString()))
        }
    }

    fun fetchNextPlaylist(nextPageToken: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.getNextPlaylists(part, channelId, key, maxResults, nextPageToken)))
        } catch (ex: Exception) {
            emit(Resource.error(data = null, message = ex.message.toString()))
        }
    }

    fun fetchDetailsList(videoListId: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.getDetailsList(part, videoListId, key, maxResults)))
        } catch (ex: Exception) {
            emit(Resource.error(data = null, message = ex.message.toString()))
        }
    }

    fun fetchNextDetailsList(videoListId: String, nextPageToken: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.getNextDetailsList(part,videoListId,key,maxResults,nextPageToken)))
        } catch (ex: Exception) {
            emit(Resource.error(data = null, message = ex.message.toString()))
        }
    }
}

class LocalRepository(context: Context){
    private val youtubeDao = YouTubeDataBase.getDatabase(context).youTubedao()

    fun getDetailsLocal(): MutableList<Item> {
        return youtubeDao.getDetailslist()
    }

    fun addDetailsLocal(list: MutableList<Item>){
        return youtubeDao.addDetailsList(list)
    }

}