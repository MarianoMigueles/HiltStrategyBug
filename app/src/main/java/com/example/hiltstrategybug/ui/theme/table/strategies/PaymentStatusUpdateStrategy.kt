package com.example.hiltstrategybug.ui.theme.table.strategies

import com.example.hiltstrategybug.data.models.tablemodels.PaymentStatus
import javax.inject.Inject

class PaymentStatusUpdateStrategy @Inject constructor() : TableUpdateStrategy<PaymentStatus> {
    override fun update(table: PaymentStatus, partialData: List<Any>): PaymentStatus {
        return table
    }
}