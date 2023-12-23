package com.example.jetsub.repository

import com.example.jetsub.db.MenuDao
import com.example.jetsub.db.MenuEntity
import kotlinx.coroutines.flow.Flow

class MenuRepository(
    private val dao: MenuDao
) {

    fun getCart(): Flow<List<MenuEntity>> {
        return dao.getCart()
    }

    suspend fun getMenuByImage(imageId: Int): MenuEntity? {
        return dao.getMenuByImage(imageId)
    }

    fun getMenuByImageAsFlow(imageId: Int): Flow<MenuEntity?> {
        return dao.getMenuByImageAsFlow(imageId)
    }

    suspend fun insertMenu(menuEntity: MenuEntity) {
        val menu: MenuEntity? = getMenuByImage(menuEntity.image)
        if (menu == null) {
            dao.insertMenu(menuEntity)
        } else {
            dao.updateMenu(
                menu.copy(
                    onCart = menu.onCart.plus(1)
                )
            )
        }
    }

    suspend fun takeFromMenu(menuEntity: MenuEntity) {
        val menu: MenuEntity? = getMenuByImage(menuEntity.image)
        if (menu != null && menu.onCart > 1) {
            dao.updateMenu(
                menu.copy(
                    onCart = menu.onCart.minus(1)
                )
            )
        } else if (menu != null) {
            dao.deleteMenu(menuEntity)
        }
    }
}