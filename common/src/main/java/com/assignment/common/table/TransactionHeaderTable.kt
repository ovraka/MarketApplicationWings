package com.assignment.common.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_header")
data class TransactionHeaderTable(
    @PrimaryKey
    @field:ColumnInfo(name = "documentCode")
    val documentCode: String,
    @field:ColumnInfo(name = "documentNumber")
    val documentNumber: String,
    @field:ColumnInfo(name = "user")
    val user: String,
    @field:ColumnInfo(name = "total")
    val total: Double,
    @field:ColumnInfo(name = "Date")
    val date: String
)