package com.example.androidmoviessample.domain.usecases

import com.example.androidmoviessample.domain.models.TrendingPeriod
import com.example.androidmoviessample.domain.repositories.ISettingsRepository

class TrendingPeriodUseCase(
    preferencesIMovieRepository: ISettingsRepository
) {
    var trendingPeriod: TrendingPeriod =
        preferencesIMovieRepository.trendingPeriod
}
