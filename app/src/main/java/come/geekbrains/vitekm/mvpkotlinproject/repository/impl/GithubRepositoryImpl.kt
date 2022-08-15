package come.geekbrains.vitekm.mvpkotlinproject.repository.impl

import come.geekbrains.vitekm.mvpkotlinproject.model.GithubUser
import come.geekbrains.vitekm.mvpkotlinproject.model.GithubUserNew
import come.geekbrains.vitekm.mvpkotlinproject.repository.GithubRepository
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class GithubRepositoryImpl : GithubRepository {

    private val userPlug = GithubUserNew("VictorMelnik", 2)

        private val repositories = listOf(
        GithubUser("MrFox"),
        GithubUser("VictorMelnik"),
        GithubUser("Denix"),
        GithubUser("DmitryWb"),
        GithubUser("Larisa")
    )


    override fun getUsers(): Single<List<GithubUser>> {
        return Single.just(repositories).delay(5L, TimeUnit.SECONDS)
    }

    fun getUserById(name: String): Single<GithubUserNew> {
        val single: Single<GithubUserNew>
        if (name == "Larisa") {
            single = Single.error(Throwable("ERROR"))
        } else {
            single = Single.just(userPlug).delay(3L, TimeUnit.SECONDS)
        }
        return single
    }


}