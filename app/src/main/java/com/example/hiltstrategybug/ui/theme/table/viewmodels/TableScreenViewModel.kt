package com.example.hiltstrategybug.ui.theme.table.viewmodels

import androidx.lifecycle.ViewModel
import com.example.hiltstrategybug.data.models.tablemodels.Table
import com.example.hiltstrategybug.di.modules.TableStrategies
import com.example.hiltstrategybug.ui.theme.table.strategies.TableUpdateStrategy
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.reflect.KClass

@HiltViewModel
class TableScreenViewModel @Inject constructor(
    @TableStrategies private val strategies: @JvmSuppressWildcards Map<KClass<out Table>, @JvmSuppressWildcards TableUpdateStrategy<*>>
) : ViewModel()