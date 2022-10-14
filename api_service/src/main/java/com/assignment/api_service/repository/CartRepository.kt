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

    fun getAllCart(username: String): LiveData<List<CartTable>> = cartDAO.getAllCart(username)

    fun insertProduct(cart: CartTable) {
        appExecutors.diskIO.execute {
            cartDAO.insertCart(cart)
        }
    }

    fun isProductOnCart(id: Int, username: String): LiveData<Boolean> = cartDAO.isProductOnCart(id, username)
}