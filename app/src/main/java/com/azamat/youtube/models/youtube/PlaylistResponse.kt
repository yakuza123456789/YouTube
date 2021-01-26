package com.azamat.youtube.models.youtube

data class PlaylistResponse(
    val etag: String,
    val items: List<Item>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfo
)