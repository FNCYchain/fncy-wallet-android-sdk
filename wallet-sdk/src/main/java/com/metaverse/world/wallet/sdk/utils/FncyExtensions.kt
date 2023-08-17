package com.metaverse.world.wallet.sdk.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import timber.log.Timber

internal fun <T> Flow<Result<T>>.catchFlowOn(ioDispatcher: CoroutineDispatcher): Flow<Result<T>> =
    this.catch { e ->
        Timber.w(e)
        e.printStackTrace()
        emit(Result.failure(e))
    }.flowOn(ioDispatcher)


internal suspend fun <T> withContextRun(
    dispatcher: CoroutineDispatcher,
    block: suspend CoroutineScope.() -> T
): Result<T> = runCatching {
    withContext(dispatcher) {
        block()
    }
}


internal fun String.toHeader() = HashMap<String, String>().apply {
    put("Authorization", "Bearer ${this@toHeader}")
}