package xyz.dudedayaworks.jetpackcompose.playground.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import xyz.dudedayaworks.jetpackcompose.playground.domain.InstagramHeaderModel

class MainViewModel : ViewModel() {
    private val _items = MutableStateFlow(List(200) {
        InstagramHeaderModel(
            id = it,
            title = "#Title $it",
            isFollowed = false
        )
    })
    val items: StateFlow<List<InstagramHeaderModel>>
        get() = _items

    fun toggleFollowing(itemId: Int) {
        viewModelScope.launch {
            _items.value = _items.value.map {
                if (it.id == itemId) {
                    it.copy(isFollowed = !it.isFollowed)
                } else {
                    it
                }
            }
        }
    }
}