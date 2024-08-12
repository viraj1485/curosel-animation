package com.example.androidlibrary

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun main() {
    val handler = CoroutineExceptionHandler { coroutineContext, throwable -> }

    CoroutineScope(Dispatchers.IO).launch {
        throw Exception("")
    }
}

