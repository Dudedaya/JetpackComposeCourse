package xyz.dudedayaworks.jetpackcompose.playground.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import xyz.dudedayaworks.jetpackcompose.playground.domain.FeedPost
import xyz.dudedayaworks.jetpackcompose.playground.domain.StatisticItem

class MainViewModel : ViewModel() {

    private val _items = MutableStateFlow(List(5) { FeedPost.preview(it) })
    val items: StateFlow<List<FeedPost>>
        get() = _items

    fun onPostStatisticClick(item: FeedPost, statisticsItem: StatisticItem) {
        viewModelScope.launch {
            val newItems = _items.value.map { postItem ->
                if (postItem == item) {
                    val newStatistics = postItem.statistics.map { item ->
                        if (item.type == statisticsItem.type) {
                            item.copy(count = item.count + 1)
                        } else {
                            item
                        }
                    }
                    postItem.copy(statistics = newStatistics)
                } else {
                    postItem
                }
            }
            _items.emit(newItems)
        }
    }

    fun onDelete(item: FeedPost) {
        viewModelScope.launch {
            _items.emit(_items.value.filterNot { it.id == item.id })
        }
    }
}