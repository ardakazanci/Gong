package com.ardakazanci.gong.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object SearchHelper {
    private var job: Job? = null

    fun <T> debounce(
        delayMs: Long = 500L,
        scope: CoroutineScope,
        func: (T) -> Unit
    ): (T) -> Unit {
        job?.cancel()
        return { param: T ->
            job = scope.launch {
                delay(delayMs)
                func(param)
            }
        }
    }
}