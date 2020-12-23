package com.azamat.youtube.ui.playlists

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.azamat.youtube.R
import kotlinx.android.synthetic.main.activity_playlist.*

class PlaylistActivity : AppCompatActivity() {

    private var descAdaper = mutableListOf<String>()
    private var sizeVideoAdapter = mutableListOf<String>()
    private var imgAdapter = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist)

        recyclerViewPlay.adapter = PlaylistAdapter(descAdaper, sizeVideoAdapter, imgAdapter)
        postToList()

    }

    private fun addToList(descr: String, sizeV: String, image: Int){
        descAdaper.add(descr)
        sizeVideoAdapter.add(sizeV)
        imgAdapter.add(image)
    }

    private fun postToList(){
        for (i in 1..15){
            addToList("Self Care Before Child Care", "32 video series$i", R.drawable.profile)
        }
    }
}