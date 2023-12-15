package com.harsh.samples.geminiproapisample.infrastructure.di

import com.harsh.samples.geminiproapisample.BuildConfig
import com.harsh.samples.geminiproapisample.data.repository.InMemoryChatRepository
import com.harsh.samples.geminiproapisample.data.source.GeminiProDataSource
import com.harsh.samples.geminiproapisample.domain.repository.ChatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
object HiltAppModule {

    @Provides
    @Singleton
    fun provideGeminiProDataSource(): GeminiProDataSource {
        return GeminiProDataSource(BuildConfig.apiKey)
    }

    @Provides
    @Singleton
    fun provideChatRepository(geminiProDataSource: GeminiProDataSource): ChatRepository {
        return InMemoryChatRepository(geminiProDataSource)
    }

}
