package com.example.hiltstrategybug.ui.theme.table.strategies

import com.example.hiltstrategybug.data.models.tablemodels.Table

interface TableUpdateStrategy<T : Table> {
    fun update(table: T, partialData: List<Any>): T
}