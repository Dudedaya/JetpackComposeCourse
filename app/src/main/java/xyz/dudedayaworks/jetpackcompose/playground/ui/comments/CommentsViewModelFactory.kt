package xyz.dudedayaworks.jetpackcompose.playground.ui.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import xyz.dudedayaworks.jetpackcompose.playground.domain.model.FeedPost

@Suppress("UNCHECKED_CAST")
class CommentsViewModelFactory(
    private val feedPost: FeedPost,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommentsViewModel(feedPost) as T
    }
}