package com.example.androidmoviessample.domain.repositories

import com.example.androidmoviessample.domain.models.TrendingPeriod

interface ISettingsRepository : IPreferences {
    var trendingPeriod: TrendingPeriod
}
