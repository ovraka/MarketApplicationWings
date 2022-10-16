package com.assignment.common.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_detail")
data class TransactionDetailTable(
    @PrimaryKey
    @field:ColumnInfo(name = "documentCode")
    val documentCode: String,
    @field:ColumnInfo(name = "documentNumber")
    val documentNumber: String,
    @field:ColumnInfo(name = "productCode")
    val productCode: String,
    @field:ColumnInfo(name = "price")
    val price: Double,
    @field:ColumnInfo(name = "quantity")
    val quantity: Int,
    @field:ColumnInfo(name = "unit")
    val unit: String,
    @field:ColumnInfo(name = "subTotal")
    val subTotal: Double,
    @field:ColumnInfo(name = "currency")
    val currency: String
)