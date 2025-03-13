package com.example.hiltstrategybug.ui.theme.table.strategies

import com.example.hiltstrategybug.data.models.tablemodels.IncomeAndExpense
import javax.inject.Inject

class IncomeAndExpenseUpdateStrategy @Inject constructor() : TableUpdateStrategy<IncomeAndExpense> {
    override fun update(table: IncomeAndExpense, partialData: List<Any>): IncomeAndExpense {
        return table
    }
}