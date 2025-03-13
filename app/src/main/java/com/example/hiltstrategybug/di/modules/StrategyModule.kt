package com.example.hiltstrategybug.di.modules


import com.example.hiltstrategybug.data.models.tablemodels.IncomeAndExpense
import com.example.hiltstrategybug.data.models.tablemodels.PaymentStatus
import com.example.hiltstrategybug.data.models.tablemodels.Table
import com.example.hiltstrategybug.ui.theme.table.strategies.IncomeAndExpenseUpdateStrategy
import com.example.hiltstrategybug.ui.theme.table.strategies.PaymentStatusUpdateStrategy
import com.example.hiltstrategybug.ui.theme.table.strategies.TableUpdateStrategy
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import javax.inject.Qualifier
import kotlin.reflect.KClass

@Module
@InstallIn(SingletonComponent::class)
object StrategyModule {

    @Provides
    @IntoMap
    @TableStrategyKey(IncomeAndExpense::class)
    fun provideIncomeAndExpenseStrategy(strategy: IncomeAndExpenseUpdateStrategy): TableUpdateStrategy<IncomeAndExpense> =
        strategy

    @Provides
    @IntoMap
    @TableStrategyKey(PaymentStatus::class)
    fun providePaymentStatusStrategy(strategy: PaymentStatusUpdateStrategy): TableUpdateStrategy<PaymentStatus> =
        strategy

    @Provides
    @TableStrategies
    fun provideTableStrategiesMap(
        strategies: Map<KClass<out Table>, TableUpdateStrategy<*>>
    ): Map<KClass<out Table>, TableUpdateStrategy<*>> {
        return strategies
    }
}

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@MapKey
annotation class TableStrategyKey(val value: KClass<out Table>)

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class TableStrategies