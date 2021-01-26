package com.azamat.youtube.ui.playlists.adapter

import com.azamat.youtube.models.youtube.Item

interface OnPlaylistClick {
    fun onItemClick(item: Item)
}