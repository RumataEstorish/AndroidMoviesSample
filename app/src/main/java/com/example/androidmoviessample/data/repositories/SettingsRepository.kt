package com.example.androidmoviessample.data.repositories

import android.content.SharedPreferences
import com.example.androidmoviessample.data.extensions.enumPreference
import com.example.androidmoviessample.domain.models.TrendingPeriod
import com.example.androidmoviessample.domain.repositories.ISettingsRepository

internal class SettingsRepository(
    override val sharedPreferences: SharedPreferences
) : ISettingsRepository {
    companion object {
        private const val TRENDING_PREF = "TRENDING_PREF"
    }

    override var trendingPeriod: TrendingPeriod by enumPreference(TRENDING_PREF, TrendingPeriod.WEEK)
}
