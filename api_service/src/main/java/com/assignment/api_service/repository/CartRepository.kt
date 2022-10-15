package com.assignment.api_service.repository

import androidx.lifecycle.LiveData
import com.assignment.api_service.DataStorePreference
import com.assignment.api_service.service.CartDAO
import com.assignment.common.ext.AppExecutors
import com.assignment.common.table.CartTable


class CartRepository(
    private val cartDAO: CartDAO,
    private val appExecutors: AppExecutors
) {

    fun getAllCart(user:String): LiveData<List<CartTable>> {
        val cartData = cartDAO.getCart(user)

        return cartData
    }

    fun insertCart(cart: CartTable) {
        appExecutors.diskIO.execute {
            cartDAO.insertCart(cart)
        }
    }

    fun deleteCart(){
        cartDAO.deleteCart()
    }
}