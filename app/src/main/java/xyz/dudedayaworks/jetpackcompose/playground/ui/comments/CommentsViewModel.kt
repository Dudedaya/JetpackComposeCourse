package xyz.dudedayaworks.jetpackcompose.playground.ui.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import xyz.dudedayaworks.jetpackcompose.playground.PlaygroundApp
import xyz.dudedayaworks.jetpackcompose.playground.domain.comment.CommentRepository
import xyz.dudedayaworks.jetpackcompose.playground.domain.model.FeedPost

class CommentsViewModel(
    feedPost: FeedPost,
    private val commentRepository: CommentRepository = PlaygroundApp.commentRepository,
) : ViewModel() {

    private val _screenState = MutableStateFlow<CommentsScreenState>(CommentsScreenState.Loading)
    val screenState: StateFlow<CommentsScreenState> get() = _screenState

    init {
        loadComments(feedPost)
    }

    private fun loadComments(feedPost: FeedPost) {
        viewModelScope.launch {
            _screenState.emit(CommentsScreenState.Loading)
            commentRepository.getPostComments(feedPost.id)
                .onSuccess {
                    _screenState.emit(
                        CommentsScreenState.Comments(feedPost = feedPost, comments = it)
                    )
                }
                .onFailure {
                    TODO("Not yet implemented")
                }
        }
    }
}