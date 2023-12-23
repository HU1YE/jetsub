package com.example.jetsub.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MenuEntity::class],
    version = 2,
    exportSchema = false
)
abstract class Driverdb : RoomDatabase() {
    abstract fun dao(): MenuDao
}