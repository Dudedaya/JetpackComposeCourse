package xyz.dudedayaworks.jetpackcompose.playground.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import xyz.dudedayaworks.jetpackcompose.playground.domain.FeedPost
import xyz.dudedayaworks.jetpackcompose.playground.domain.PostComment
import xyz.dudedayaworks.jetpackcompose.playground.domain.StatisticItem
import xyz.dudedayaworks.jetpackcompose.playground.domain.StatisticType

class MainViewModel : ViewModel() {

    private val initialState = HomeScreenState.Posts(List(5) { FeedPost.preview(it) })
    private val _screenState = MutableStateFlow<HomeScreenState>(HomeScreenState.Loading)
    val screenState: StateFlow<HomeScreenState>
        get() = _screenState

    init {
        viewModelScope.launch {
            delay(2000L)
            _screenState.emit(initialState)
        }
    }

    fun onPostStatisticClick(item: FeedPost, statisticsItem: StatisticItem) {
        val postsState = _screenState.value as? HomeScreenState.Posts ?: return
        viewModelScope.launch {
            if (statisticsItem.type == StatisticType.COMMENTS) {
                goToPosts(item)
            } else {
                updateStatistics(postsState, item, statisticsItem)
            }
        }
    }

    private suspend fun goToPosts(feedPost: FeedPost) {
        _screenState.emit(HomeScreenState.Loading)
        delay(1000L)
        val comments = List(50) { PostComment.preview(it) }
        _screenState.emit(HomeScreenState.Comments(feedPost = feedPost, comments = comments))
    }

    private suspend fun updateStatistics(
        postsState: HomeScreenState.Posts,
        item: FeedPost,
        statisticsItem: StatisticItem,
    ) {
        val newItems = postsState.posts.map { postItem ->
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
        _screenState.emit(HomeScreenState.Posts(posts = newItems))
    }

    fun onDeletePost(item: FeedPost) {
        val postsState = _screenState.value as? HomeScreenState.Posts ?: return
        viewModelScope.launch {
            val newPosts = postsState.posts.filterNot { it.id == item.id }
            _screenState.emit(HomeScreenState.Posts(posts = newPosts))
        }
    }

    fun onReturnToPosts() {
        viewModelScope.launch {
            _screenState.emit(HomeScreenState.Loading)
            delay(1000L)
            _screenState.emit(initialState)
        }
    }
}