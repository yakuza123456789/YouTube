package com.azamat.youtube.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.azamat.youtube.R
import com.azamat.youtube.models.resource_courutines.Resource
import com.azamat.youtube.models.resource_courutines.Status
import com.azamat.youtube.models.youtube.Item
import com.azamat.youtube.models.youtube.PlaylistResponse
import com.azamat.youtube.ui.playlists.PlaylistFragment
import com.azamat.youtube.ui.playlists.adapter.OnPlaylistClick
import com.azamat.youtube.utils.showToast
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : Fragment(), OnPlaylistClick {

    private lateinit var viewModel: DetailsViewHolder
    private lateinit var adapter: DetailsAdapter
    private lateinit var list: Item
    private var nextPageList: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            list = it.getSerializable(PlaylistFragment.PLAYLIST_KEY) as Item
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailsViewHolder::class.java)
        viewModel.initRepository(requireContext())
        initRecyclerAdapter()
        fetchData()
        pagination()
    }

    private fun pagination() {
        viewModel.deleteAll()
        nested_scroll.setOnScrollChangeListener { nested: NestedScrollView, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == nested.getChildAt(0).measuredHeight - nested.measuredHeight) {
                nextPageList?.let {
                    fetchNextList(nextPageList!!)
                }
            }
        }
    }

    private fun fetchNextList(nextPageList: String) {
        viewModel.fetchNextDetailsList(list.id, nextPageList).observe(viewLifecycleOwner, Observer {
            if (it?.data?.nextPageToken == null) {
                this.nextPageList = null
            }
            statusCheck(it)
        })
    }

    private fun statusCheck(it: Resource<PlaylistResponse>?) {
        when (it?.status) {
            Status.SUCCESS -> setData(it)
            Status.ERROR -> context?.showToast(it.message ?: "ERROR")
        }
    }

    private fun setData(it: Resource<PlaylistResponse>) {
        it.data?.items?.let { it1 ->
            adapter.add(it1)
            viewModel.addDetailsToDB(it1 as MutableList<Item>)
        }
        nextPageList = it.data?.nextPageToken
    }

    private fun fetchData() {
        viewModel.fetchDetailsListFromServer(list.id).observe(viewLifecycleOwner, Observer {
            statusCheck(it)
        })
        playlist_title.text = list.snippet.title
        playlist_description.text = list.snippet.channelTitle
    }

    private fun initRecyclerAdapter() {
        adapter = DetailsAdapter(this)
        detailsRecycler.adapter = adapter
    }

    override fun onItemClick(item: Item) {

    }


}