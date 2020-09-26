package com.shaugh.ecomerceandsocialapp.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PostEntity::class], version = 1, exportSchema = true)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun dbDao(): DbDao
}