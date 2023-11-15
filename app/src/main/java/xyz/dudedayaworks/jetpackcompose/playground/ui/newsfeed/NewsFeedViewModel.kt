package xyz.dudedayaworks.jetpackcompose.playground.ui.newsfeed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import xyz.dudedayaworks.jetpackcompose.playground.PlaygroundApp
import xyz.dudedayaworks.jetpackcompose.playground.domain.model.FeedPost
import xyz.dudedayaworks.jetpackcompose.playground.domain.model.StatisticItem
import xyz.dudedayaworks.jetpackcompose.playground.domain.post.PostRepository

class NewsFeedViewModel(
    postRepository: PostRepository = PlaygroundApp.postRepository,
) : ViewModel() {

    private val _screenState = MutableStateFlow<NewsFeedScreenState>(NewsFeedScreenState.Loading)
    val screenState: StateFlow<NewsFeedScreenState>
        get() = _screenState

    init {
        viewModelScope.launch {
            postRepository.getPosts()
                .onSuccess {
                    _screenState.emit(NewsFeedScreenState.Posts(it))
                }
                .onFailure {
                    TODO("Not yet implemented")
                }
        }
    }

    fun onPostStatisticClick(item: FeedPost, statisticsItem: StatisticItem) {
        val postsState = _screenState.value as? NewsFeedScreenState.Posts ?: return
        viewModelScope.launch {
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
            _screenState.emit(NewsFeedScreenState.Posts(posts = newItems))
        }
    }

    fun onDeletePost(item: FeedPost) {
        val postsState = _screenState.value as? NewsFeedScreenState.Posts ?: return
        viewModelScope.launch {
            val newPosts = postsState.posts.filterNot { it.id == item.id }
            _screenState.emit(NewsFeedScreenState.Posts(posts = newPosts))
        }
    }
}