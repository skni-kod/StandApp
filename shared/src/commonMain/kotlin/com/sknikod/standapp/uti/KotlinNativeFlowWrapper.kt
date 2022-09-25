package com.sknikod.standapp.uti

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class KotlinNativeFlowWrapper {
    private val scope: CoroutineScope = object : CoroutineScope {
        override val coroutineContext: CoroutineContext
            get() = SupervisorJob() + Dispatchers.Main
    }

    fun <T> subscribe(
        scope: CoroutineScope = this.scope,
        flow: Flow<T>,
        onEach: (item: T) -> Unit,
        onComplete: () -> Unit,
        onThrow: (error: Throwable) -> Unit
    ) = flow
        .onEach { onEach(it) }
        .catch { onThrow(it) }
        .onCompletion { onComplete() }
        .launchIn(scope)

    fun <T> launchFunction(
        scope: CoroutineScope = this.scope,
        function: suspend () -> T,
        onComplete: (item: T) -> Unit,
        onThrow: (error: Throwable) -> Unit
    ) {
        this.scope.launch {
            try {
                onComplete(function())
            } catch (e: Throwable) {
                onThrow(e)
            }
        }
    }
}
