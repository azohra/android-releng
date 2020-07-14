package co.ld

import co.ld.codechallenge.repository.SearchRepository
import co.ld.codechallenge.viewmodel.GithubViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    // You can name it anything, such as `poke()`. It doesn't have to be `inject()`.
    fun searchRepository(searchRepository: SearchRepository)
    fun githubViewModel(githubViewModel: GithubViewModel)
}