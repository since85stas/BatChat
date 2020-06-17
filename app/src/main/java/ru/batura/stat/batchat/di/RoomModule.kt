package ru.batura.stat.batchat.di

import android.content.Context
import androidx.room.Room
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.batura.stat.batchat.repository.room.ChatDao
import ru.batura.stat.batchat.repository.room.ChatDatabase
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RoomModule {

    @Provides
    fun provideChatDao(database: ChatDatabase): ChatDao {
        return database.chatDatabaseDao
    }

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext appContext: Context): ChatDatabase {
        return ChatDatabase.getInstance(appContext)
    }

}