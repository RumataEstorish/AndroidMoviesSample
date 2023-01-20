package com.example.androidmoviessample.common

import kotlinx.coroutines.CoroutineDispatcher

interface IDispatchersProvider {
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}