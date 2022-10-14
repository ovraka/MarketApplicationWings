package com.assignment.api_service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.assignment.api_service.service.CartDAO
import com.assignment.common.table.CartTable

@Database(entities = [CartTable::class], version = 1, exportSchema = false)
abstract class CartDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDAO

    companion object {
        @Volatile
        private var instance: CartDatabase? = null
        fun getInstance(context: Context): CartDatabase = instance ?: Room.databaseBuilder(
            context.applicationContext, CartDatabase::class.java, "Cart.db"
        ).build()
    }
}