package xyz.dudedayaworks.jetpackcompose.playground.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {
    private val _isFollowing = MutableStateFlow(false)
    val isFollowing: StateFlow<Boolean> get() = _isFollowing

    fun toggleFollowing() {
        _isFollowing.value = !_isFollowing.value
    }
}