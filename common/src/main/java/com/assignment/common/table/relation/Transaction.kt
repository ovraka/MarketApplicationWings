package com.assignment.common.table.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.assignment.common.table.TransactionDetailTable
import com.assignment.common.table.TransactionHeaderTable
import kotlinx.parcelize.RawValue

data class Transaction(
    @Embedded
    val transactionHeaderTable: TransactionHeaderTable,

    @Relation(
        parentColumn = "documentNumber",
        entityColumn = "documentNumber"
    )

    val transactionDetailTable: List<TransactionDetailTable>
)
