package com.assignment.api_service.service

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.common.table.CartTable

@Dao
interface CartDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCart(cart: CartTable)

    @Query("DELETE FROM cart WHERE status = 'pending' AND userId=:username AND productCode=:id")
    fun deleteProductFromCart(id: Int, username: String)

    @Query("SELECT * from cart WHERE status = 'pending' AND userId=:username ORDER BY id ASC")
    fun getAllCart(username: String): LiveData<List<CartTable>>

    @Query("SELECT EXISTS(SELECT * FROM cart WHERE productCode = :id AND status ='pending' AND userId=:username)")
    fun isProductExists(id: Int, username: String): Boolean

    @Query("SELECT EXISTS(SELECT * FROM cart WHERE productCode = :id AND status = 'pending' AND userId=:username)")
    fun isProductOnCart(id: Int, username: String): LiveData<Boolean>
}