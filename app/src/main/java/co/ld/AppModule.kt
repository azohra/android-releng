package co.ld

import co.ld.codechallenge.data.RepositoryManager
import co.ld.codechallenge.network.NetworkManager
import co.ld.codechallenge.repository.SearchRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideNetworkManager(): NetworkManager {
        return NetworkManager()
    }

    @Provides
    @Singleton
    fun provideRepositoryManager(): RepositoryManager {
        return RepositoryManager()
    }

    @Provides
    @Singleton
    fun provideSearchRepository(): SearchRepository {
        return SearchRepository()
    }
}