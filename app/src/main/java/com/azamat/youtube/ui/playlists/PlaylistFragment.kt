package com.azamat.youtube.ui.playlists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.azamat.youtube.R
import com.azamat.youtube.models.resource_courutines.Resource
import com.azamat.youtube.models.resource_courutines.Status
import com.azamat.youtube.models.youtube.Item
import com.azamat.youtube.models.youtube.PlaylistResponse
import com.azamat.youtube.ui.playlists.adapter.OnPlaylistClick
import com.azamat.youtube.ui.playlists.adapter.PlaylistAdapter
import com.azamat.youtube.utils.showToast
import kotlinx.android.synthetic.main.fragment_playlist.*


 class PlaylistFragment : Fragment(), OnPlaylistClick {

    companion object{
        const val PLAYLIST_KEY = "key"
    }

    private lateinit var adapter: PlaylistAdapter
    private lateinit var viewModel: PlaylistViewModel
    private var nextPlayList: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_playlist, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlaylistViewModel::class.java)
        activeRecycler()
        fetchData()
        pagination()
    }

     private fun fetchData(){
         viewModel.fetchPlaylistFromServer().observe(viewLifecycleOwner, Observer {
             statusCheck(it)
         })
     }

    private fun pagination() {
        playlist_scroll.setOnScrollChangeListener { nested: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (scrollY == nested.getChildAt(0).measuredHeight - nested.measuredHeight) {
                nextPlayList?.let {
                    fetchNextList(nextPlayList!!)
                }
            }
        }
    }

    private fun fetchNextList(nextPageList: String) {
        viewModel.fetchNextPlaylist(nextPageList).observe(viewLifecycleOwner, Observer {
            if (it?.data?.nextPageToken == null) {
                this.nextPlayList = null
            }
            statusCheck(it)
        })
    }

    private fun statusCheck(it: Resource<PlaylistResponse>?) {
        when(it?.status){
            Status.SUCCESS -> setData(it)
            Status.ERROR -> context?.showToast(it.message?: "ERROR")
        }

    }

    private fun setData(it: Resource<PlaylistResponse>) {
        it.data?.items?.let { it1 -> adapter.add(it1)  }
        nextPlayList = it.data?.nextPageToken

    }

    private fun activeRecycler() {
        adapter = PlaylistAdapter(this)
        playListRecycler.adapter = adapter
    }

    override fun onItemClick(item: Item) {
        val bundle = Bundle()
        bundle.putSerializable(PLAYLIST_KEY, item)
        findNavController().navigate(R.id.action_playlistFragment_to_detailsFragment, bundle)
    }


}