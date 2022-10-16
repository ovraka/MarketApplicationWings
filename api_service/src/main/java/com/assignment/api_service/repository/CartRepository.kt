package com.assignment.api_service.repository

import androidx.lifecycle.LiveData
import com.assignment.api_service.DataStorePreference
import com.assignment.api_service.service.CartDAO
import com.assignment.common.ext.AppExecutors
import com.assignment.common.ext.Count
import com.assignment.common.table.CartTable
import com.assignment.common.table.TransactionDetailTable
import com.assignment.common.table.TransactionHeaderTable
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.random.Random


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

    fun insertTransaction(cartList: List<CartTable>, user: String) {
        val documentCode = LocalDateTime.now().toString()
        val date = LocalDate.now().toString()
        val documentNumber = LocalTime.now().toString()
        val transactionDetail = arrayListOf<TransactionDetailTable>()
        cartList.forEach {
            transactionDetail.add(
                TransactionDetailTable(
                    documentCode+ it.productCode,
                    documentNumber,
                    it.productCode,
                    Count.getPrice(it.price, it.discount),
                    it.quantity,
                    it.unit,
                    Count.getSubTotal(it),
                    it.currency
                )
            )
        }
        appExecutors.diskIO.execute {
            cartDAO.insertTransactionHeader(
                TransactionHeaderTable(
                    documentCode,
                    documentNumber,
                    user,
                    Count.getTotal(cartList),
                    date
                )
            )
            cartDAO.insertTransactionDetail(transactionDetail)
        }
    }
}