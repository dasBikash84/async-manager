package com.dasbikash.async_manager

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Extension function to launch async task
 * suspending any suspension function
 *
 * @param task posted functional parameter
 * */
suspend fun <T:Any> runSuspended(task:()->T?):T? {
    coroutineContext().let {
        return withContext(it) {
            return@withContext async(Dispatchers.IO) { task() }.await()
        }
    }
}

/**
 * Extension function on access CoroutineContext from inside of any suspension function
 *
 * @return subject CoroutineContext
 * */
suspend fun coroutineContext(): CoroutineContext = suspendCoroutine { it.resume(it.context) }
