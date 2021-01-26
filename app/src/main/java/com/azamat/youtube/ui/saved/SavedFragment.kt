package com.azamat.youtube.ui.saved

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.azamat.youtube.R
import com.azamat.youtube.models.youtube.Item
import com.azamat.youtube.ui.details.DetailsAdapter
import com.azamat.youtube.ui.playlists.adapter.OnPlaylistClick
import kotlinx.android.synthetic.main.saved_fragment.*

class SavedFragment : Fragment(), OnPlaylistClick {

    private lateinit var viewModel: SavedViewModel
    private lateinit var adapter: DetailsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.saved_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SavedViewModel::class.java)
        viewModel.initRepository(requireContext())
        initRecyclerAdapter()
        fetchData()
    }

    private fun fetchData() {
        viewModel.getDetailsFromDB()
        viewModel.localData.observe(viewLifecycleOwner, Observer {
            adapter.add(it)
        })
    }

    private fun initRecyclerAdapter() {
        adapter = DetailsAdapter(this)
        savedRecycler.adapter = adapter
    }

    override fun onItemClick(item: Item) {

    }

}