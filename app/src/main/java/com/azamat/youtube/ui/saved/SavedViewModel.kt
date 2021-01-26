package com.azamat.youtube.ui.saved

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azamat.youtube.models.youtube.Item
import com.azamat.youtube.repository.LocalRepository

class SavedViewModel : ViewModel() {
    var localData = MutableLiveData<MutableList<Item>>()
    lateinit var localRepo: LocalRepository

    fun initRepository( context: Context) {
        localRepo = LocalRepository(context)
    }

    fun getDetailsFromDB() {
        localData.value = localRepo.getDetailsLocal()
    }
}