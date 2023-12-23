package com.example.jetsub.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuDao {

    @Query("SELECT * FROM menu")
    fun getCart(): Flow<List<MenuEntity>>

    @Query("SELECT * FROM menu WHERE image =:imageId LIMIT 1 ")
    suspend fun getMenuByImage(imageId: Int): MenuEntity?

    @Query("SELECT * FROM menu WHERE image =:imageId LIMIT 1 ")
    fun getMenuByImageAsFlow(imageId: Int): Flow<MenuEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenu(menuEntity: MenuEntity)

    @Update
    suspend fun updateMenu(menuEntity: MenuEntity)

    @Delete
    suspend fun deleteMenu(menuEntity: MenuEntity)
}