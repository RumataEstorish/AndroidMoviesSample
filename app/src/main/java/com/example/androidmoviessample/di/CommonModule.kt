package com.example.androidmoviessample.di

import com.example.androidmoviessample.common.DispatchersProvider
import com.example.androidmoviessample.common.IDispatchersProvider
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.binds
import org.koin.dsl.module

val commonModule = module {
    factoryOf(::DispatchersProvider) binds arrayOf(IDispatchersProvider::class)
}