package com.azamat.youtube.ui.playlists.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azamat.youtube.R
import com.azamat.youtube.models.youtube.Item
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_playlist.view.*
import java.util.ArrayList

class PlaylistAdapter(var onPlaylistClick: OnPlaylistClick) :
    RecyclerView.Adapter<PlaylistAdapter.PlayViewHolder>() {

    var list = ArrayList<Item>()

    fun add(data: List<Item>) {
        list.addAll(data)
        notifyDataSetChanged()
    }

    class PlayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView.rootView) {


        fun onBind(item: Item){
            itemView.apply {
                tvTitle.text = item.snippet?.title
                tvAmountViews.text = item.contentDetails?.itemCount.toString() +  "video numbers"
                Glide.with(this).load(item.snippet?.thumbnails?.medium?.url).into(ivVideo)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_playlist, parent, false)
        return PlayViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PlayViewHolder, position: Int) {
        holder.onBind(list[position])
        holder.itemView.setOnClickListener {
            onPlaylistClick.onItemClick(list[position])
        }
    }


}

