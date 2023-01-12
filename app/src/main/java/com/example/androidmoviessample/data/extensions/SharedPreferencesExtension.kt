@file:Suppress("unused")

package com.example.androidmoviessample.data.extensions

import com.example.androidmoviessample.domain.repositories.IPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

fun <T : Enum<*>> enumPreference(key: String, clazz: KClass<T>, defaultValue: T) =
    object : ReadWriteProperty<IPreferences, T> {

        override fun getValue(thisRef: IPreferences, property: KProperty<*>): T {
            val value = thisRef.sharedPreferences.getInt(key, defaultValue.ordinal)
            return clazz.java.enumConstants!!.first { it.ordinal == value }
        }

        override fun setValue(thisRef: IPreferences, property: KProperty<*>, value: T) {
            thisRef.sharedPreferences.edit().putInt(key, value.ordinal).apply()
        }
    }

inline fun <reified T : Enum<*>> enumPreference(key: String, defaultValue: T) =
    object : ReadWriteProperty<IPreferences, T> {

        override fun getValue(thisRef: IPreferences, property: KProperty<*>): T {
            val value = thisRef.sharedPreferences.getInt(key, defaultValue.ordinal)
            return T::class.java.enumConstants!!.first { it.ordinal == value }
        }

        override fun setValue(thisRef: IPreferences, property: KProperty<*>, value: T) {
            thisRef.sharedPreferences.edit().putInt(key, value.ordinal).apply()
        }
    }
