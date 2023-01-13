package com.example.androidmoviessample.domain.usecases

import com.example.androidmoviessample.domain.models.TrendingPeriod
import com.example.androidmoviessample.domain.repositories.ISettingsRepository

class TrendingPeriodUseCase(
    private val settingsRepository: ISettingsRepository
) {
    var trendingPeriod: TrendingPeriod
        get() = settingsRepository.trendingPeriod
        set(value) {
            settingsRepository.trendingPeriod = value
        }
}
