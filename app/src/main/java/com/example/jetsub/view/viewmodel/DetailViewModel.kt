package com.example.jetsub.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetsub.db.MenuEntity
import com.example.jetsub.model.Menu
import com.example.jetsub.repository.MenuRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: MenuRepository
) : ViewModel() {

    private val _menuOnCart: MutableStateFlow<Int> = MutableStateFlow(0)
    val menuOnCart: StateFlow<Int> = _menuOnCart

    fun getMenuByImage(imageId: Int) = viewModelScope.launch {
        repository.getMenuByImageAsFlow(imageId).collectLatest {result ->
            _menuOnCart.update {
                result?.onCart ?: 0
            }
        }
    }

    fun addMenu(menu: Menu) = viewModelScope.launch {
        repository.insertMenu(
            MenuEntity(
                image = menu.image,
                title = menu.title,
                national = menu.national,
                category = menu.category,
                onCart = 1,
                desc = menu.desc
            )
        )
    }

    fun takeFromMenu(menu: Menu) = viewModelScope.launch {
        repository.takeFromMenu(
            MenuEntity(
                image = menu.image,
                title = menu.title,
                national = menu.national,
                category = menu.category,
                onCart = 1,
                desc = menu.desc
            )
        )
    }
}