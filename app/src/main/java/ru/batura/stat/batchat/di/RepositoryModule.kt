package ru.batura.stat.batchat.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import ru.batura.stat.batchat.repository.IRepository
import ru.batura.stat.batchat.repository.Repository

@Module
@InstallIn(ActivityComponent::class)
abstract class RepositoryModule {

    @Binds abstract fun bindRepos(repository: Repository): IRepository
}