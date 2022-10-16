package com.assignment.api_service.service

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.common.table.CartTable
import com.assignment.common.table.TransactionDetailTable
import com.assignment.common.table.TransactionHeaderTable

@Dao
interface CartDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCart(cart: CartTable)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTransactionHeader(transactionHeaderTable: TransactionHeaderTable)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTransactionDetail(transactionDetailTable: List<TransactionDetailTable>)

    @Query("SELECT * FROM cart WHERE user=:user")
    fun getCart(user: String): LiveData<List<CartTable>>

    @Query("DELETE FROM cart")
    fun deleteCart():Unit

//    @Query("DELETE FROM cart WHERE status = 'pending' AND userId=:username AND productCode=:id")
//    fun deleteProductFromCart(id: Int, username: String)
//
//    @Query("SELECT * from cart WHERE status = 'pending' AND userId=:username ORDER BY id ASC")
//    fun getAllCart(username: String): LiveData<List<CartTable>>
//
//    @Query("SELECT EXISTS(SELECT * FROM cart WHERE productCode = :id AND status ='pending' AND userId=:username)")
//    fun isProductExists(id: Int, username: String): Boolean
//
//    @Query("SELECT EXISTS(SELECT * FROM cart WHERE productCode = :id AND status = 'pending' AND userId=:username)")
//    fun isProductOnCart(id: Int, username: String): LiveData<Boolean>
}