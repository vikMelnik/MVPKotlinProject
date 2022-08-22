package come.geekbrains.vitekm.mvpkotlinproject.allinterface

import come.geekbrains.vitekm.mvpkotlinproject.model.GithubUser
import io.reactivex.rxjava3.core.Single

interface GithubRepository {

    fun getUsers(): Single<List<GithubUser>>
    fun getUserById(login: String): Single<GithubUser>
}