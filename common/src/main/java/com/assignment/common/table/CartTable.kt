package com.assignment.common.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
class CartTable (
    @PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = "id")
    var id: Int,
    @field:ColumnInfo(name = "productCode")
    val productCode: String,
    @field:ColumnInfo(name = "name")
    val name: String,
    @field:ColumnInfo(name = "price")
    val price: Double,
    @field:ColumnInfo(name = "currency")
    val currency: String,
    @field:ColumnInfo(name = "discount")
    val discount: Double,
    @field:ColumnInfo(name = "dimension")
    val dimension: String,
    @field:ColumnInfo(name = "unit")
    val unit: String,
    @field:ColumnInfo(name = "userId")
    val userId: String,
    @field:ColumnInfo(name = "status")
    val status: String
) {
    constructor(
        productCode: String,
        name: String,
        price: Double,
        currency: String,
        discount: Double,
        dimension: String,
        unit: String,
        userId: String,
        status: String
    ) : this(0, productCode, name, price, currency, discount, dimension, unit, userId, status)
}