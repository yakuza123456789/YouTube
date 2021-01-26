package com.azamat.youtube.data.network.retrofit

import com.azamat.youtube.models.youtube.PlaylistResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApi   {

    @GET("youtube/v3/playlists")
    suspend fun fetchPlaylists(
        @Query("part") part:String,
        @Query("channelId") channelId:String,
        @Query("key") key:String,
        @Query("maxResults") maxResults: Int
    ):PlaylistResponse

    @GET("youtube/v3/playlists")
    suspend fun getNextPlaylists(
        @Query("part")part : String,
        @Query("channelId") channelId:String,
        @Query("key") key : String,
        @Query("maxResults") maxResults : Int,
        @Query("pageToken") nextPageToken : String? = null
    ): PlaylistResponse

    @GET("youtube/v3/playlistItems")
    suspend fun getDetailsList(
        @Query("part")part : String,
        @Query("playlistId") playlistId:String,
        @Query("key") key : String,
        @Query("maxResults") maxResults : Int
    ) : PlaylistResponse

    @GET("youtube/v3/playlistItems")
    suspend fun getNextDetailsList(
        @Query("part")part : String,
        @Query("playlistId") playlistId:String,
        @Query("key") key : String,
        @Query("maxResults") maxResults : Int,
        @Query("pageToken") nextPageToken : String? = null
    ) : PlaylistResponse
}