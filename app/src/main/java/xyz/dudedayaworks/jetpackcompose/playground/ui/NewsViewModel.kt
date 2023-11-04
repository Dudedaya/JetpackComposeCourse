package xyz.dudedayaworks.jetpackcompose.playground.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import xyz.dudedayaworks.jetpackcompose.playground.domain.PostItem
import xyz.dudedayaworks.jetpackcompose.playground.domain.StatisticItem

class NewsViewModel : ViewModel() {

    private val _postItem = MutableStateFlow(PostItem.PREVIEW)
    val postItem: StateFlow<PostItem>
        get() = _postItem

    fun onPostStatisticClick(type: StatisticItem) {
        val newStatistics = _postItem.value.statistics.map { item ->
            if (item.type == type.type) {
                item.copy(count = item.count + 1)
            } else {
                item
            }
        }
        _postItem.value = _postItem.value.copy(statistics = newStatistics)
    }
}