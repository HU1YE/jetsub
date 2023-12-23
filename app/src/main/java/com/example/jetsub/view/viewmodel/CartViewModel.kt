package com.example.jetsub.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetsub.model.Menu
import com.example.jetsub.repository.MenuRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: MenuRepository
) : ViewModel() {

    private val _menus: MutableStateFlow<List<Menu>> = MutableStateFlow(emptyList())
    val menus = _menus.asStateFlow()

    private fun getCart() = viewModelScope.launch {
        repository.getCart().collectLatest { result ->
            _menus.update {
                val mappedMenu = result.map {
                    Menu(
                        image = it.image,
                        title = it.title,
                        category = it.category,
                        national = it.national,
                        onCart = it.onCart,
                        desc = it.desc
                    )
                }
                mappedMenu
            }
        }
    }

    init {
        getCart()
    }
}