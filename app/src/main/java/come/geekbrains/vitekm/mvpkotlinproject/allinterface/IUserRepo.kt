package come.geekbrains.vitekm.mvpkotlinproject.allinterface

import come.geekbrains.vitekm.mvpkotlinproject.model.GithubUser
import come.geekbrains.vitekm.mvpkotlinproject.model.GithubUserNew
import come.geekbrains.vitekm.mvpkotlinproject.model.Repository
import io.reactivex.rxjava3.core.Single


interface IUserRepo {
    fun getUsers(): Single<List<GithubUser>>

    fun getUserByLogin(login: String): Single<GithubUserNew>

    fun getUserRepos(
        login: String,
        type: String?,
        sort: String?,
        direction: String?,
        perPage: Int?,
        page: Int?
    ): Single<List<Repository>>

    fun getRepositoryByUrl(url: String): Single<Repository>
}