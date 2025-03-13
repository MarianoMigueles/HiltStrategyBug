# HiltStrategyBug
I'm trying to inject a Map of strategies into my ViewModel using Hilt. Each strategy is associated with a different table class, and I'm using a custom annotation (TableStrategyKey) as the map key.

```
@Module
@InstallIn(SingletonComponent::class)
object StrategyModule {

    @Provides
    @IntoMap
    @TableStrategyKey(IncomeAndExpense::class)
    fun provideIncomeAndExpenseStrategy(strategy: IncomeAndExpenseUpdateStrategy): TableUpdateStrategy<*> =
        strategy

    @Provides
    @IntoMap
    @TableStrategyKey(PaymentStatus::class)
    fun providePaymentStatusStrategy(strategy: PaymentStatusUpdateStrategy): TableUpdateStrategy<*> =
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
```

I trying to inject this strategy to my `ViewModel`: 
```
@HiltViewModel
class TableScreenViewModel @Inject constructor(
    @TableStrategies private val strategies: @JvmSuppressWildcards Map<KClass<out Table>, @JvmSuppressWildcards TableUpdateStrategy<*>>
) : ViewModel()
```

But i'm getting the following build error:

```
C:\Users\mar\AndroidStudioProjects\HiltStrategyBug\app\build\generated\hilt\component_sources\debug\com\example\hiltstrategybug\HiltStrategyBugApplication_HiltComponents.java:137: error: [Dagger/MissingBinding] java.util.Map<kotlin.reflect.KClass<? extends com.example.hiltstrategybug.data.models.tablemodels.Table>,? extends com.example.hiltstrategybug.ui.theme.table.strategies.TableUpdateStrategy<?>> cannot be provided without an @Provides-annotated method.
  public abstract static class SingletonC implements HiltStrategyBugApplication_GeneratedInjector,
                         ^
```
### What am I doing wrong? How can I fix this error and get Hilt to correctly inject the Map of strategies into my ViewModel?

I have verified that I have the @Provides method that provides the complete Map in my Hilt module, and that all dependencies are configured correctly and I have tried cleaning the Hilt cache, invalidating the Android Studio cache, verifying the @Inject annotations, and ensuring that all dependencies are configured correctly.
