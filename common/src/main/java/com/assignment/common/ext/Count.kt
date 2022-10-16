package com.assignment.common.ext

import com.assignment.common.table.CartTable

object Count {
    fun getSubTotal(cartTable: CartTable): Double {
        return (cartTable.price -(cartTable.price * cartTable.discount / 100)) * cartTable.quantity
    }
    fun getPrice(price: Double, discount: Double) = price - (price * discount / 100)

    fun getTotal(subTotalList: List<CartTable>): Double {
        var total = 0.0
        subTotalList.forEach {
            total += getSubTotal(it)
        }
        return total
    }
}