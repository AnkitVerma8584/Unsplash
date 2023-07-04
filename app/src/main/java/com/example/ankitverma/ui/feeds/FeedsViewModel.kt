package com.example.ankitverma.ui.feeds

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ankitverma.domain.modals.Feeds
import com.example.ankitverma.domain.repository.FeedsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedsViewModel @Inject constructor(private val feedsRepository: FeedsRepository) :
    ViewModel() {

    private val _feeds = MutableLiveData<List<Feeds>>()
    val feeds: LiveData<List<Feeds>> = _feeds

    init {
        getFeeds()
    }

    fun getFeeds() {
        viewModelScope.launch {
            val list = feedsRepository.getFeeds()
            _feeds.postValue(list)
        }
    }
}