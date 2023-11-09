package xyz.dudedayaworks.jetpackcompose.playground.ui.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import xyz.dudedayaworks.jetpackcompose.playground.domain.FeedPost
import xyz.dudedayaworks.jetpackcompose.playground.domain.PostComment

class CommentsViewModel(
    feedPost: FeedPost,
) : ViewModel() {

    private val _screenState = MutableStateFlow<CommentsScreenState>(CommentsScreenState.Loading)
    val screenState: StateFlow<CommentsScreenState> get() = _screenState

    init {
        loadComments(feedPost)
    }

    private fun loadComments(feedPost: FeedPost) {
        viewModelScope.launch {
            _screenState.emit(CommentsScreenState.Loading)
            delay(1000L)
            val comments = List(50) { PostComment.preview(it) }
            _screenState.emit(
                CommentsScreenState.Comments(feedPost = feedPost, comments = comments)
            )
        }
    }
}