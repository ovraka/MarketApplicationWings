package com.assignment.common.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "cart", indices = [Index(value = ["productCode"], unique = true)])
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
    @field:ColumnInfo(name = "user")
    val user: String,
    @field:ColumnInfo(name ="quantity")
    var quantity: Int

) {

}