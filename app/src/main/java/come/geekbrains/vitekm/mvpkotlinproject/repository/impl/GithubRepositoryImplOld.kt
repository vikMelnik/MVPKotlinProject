package come.geekbrains.vitekm.mvpkotlinproject.repository.impl

import come.geekbrains.vitekm.mvpkotlinproject.model.GithubUser
import come.geekbrains.vitekm.mvpkotlinproject.repository.GithubRepository
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class GithubRepositoryImplOld : GithubRepository {

    private val userPlug = GithubUser(2,"VictorMelnik", "https://api.github.com/users/VictorMelnik")

        private val repositories = listOf(
        GithubUser(1,"MrFox", ""),
        GithubUser(2,"VictorMelnik", ""),
        GithubUser(3,"Denix", ""),
        GithubUser(4,"DmitryWb", ""),
        GithubUser(5,"Larisa", "")
    )


    override fun getUsers(): Single<List<GithubUser>> {
        return Single.just(repositories).delay(5L, TimeUnit.SECONDS)
    }

    override fun getUserById(name: String): Single<GithubUser> {
        val single: Single<GithubUser>
        if (name == "Larisa") {
            single = Single.error(Throwable("ERROR"))
        } else {
            single = Single.just(userPlug).delay(3L, TimeUnit.SECONDS)
        }
        return single
    }


}