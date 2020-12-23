package com.azamat.youtube.ui.playlists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.azamat.youtube.R
import kotlinx.android.synthetic.main.item_playlist.view.*
import java.util.ArrayList

class PlaylistAdapter( private var descriptionL: List<String>, private var videoL: List<String>, private var imageL: List<Int>) :
    RecyclerView.Adapter<PlaylistAdapter.PlayViewHolder>() {

      class PlayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var itemDesc : TextView = itemView.tvDescPL
            var itemSizeVideo : TextView = itemView.tvSizeVideo
            var itemImage : ImageView = itemView.imgPlaylist

          init {
              itemView.setOnClickListener {
                  val position: Int = adapterPosition
                  Toast.makeText(itemView.context, "Позиция $position", Toast.LENGTH_SHORT).show()
              }
          }
      }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_playlist, parent, false)
        return PlayViewHolder(view)
    }

    override fun getItemCount(): Int {
        return descriptionL.size
    }

    override fun onBindViewHolder(holder: PlayViewHolder, position: Int) {
        holder.itemDesc.text = descriptionL[position]
        holder.itemSizeVideo.text = videoL[position]
        holder.itemImage.setImageResource(imageL[position])
    }


}

