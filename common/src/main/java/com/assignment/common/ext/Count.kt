package com.assignment.common.ext

import com.assignment.common.table.CartTable

object Count {
    fun getSubTotal(cartTable: CartTable): Double {
        return (cartTable.price -(cartTable.price * cartTable.discount / 100)) * cartTable.quantity
    }


}