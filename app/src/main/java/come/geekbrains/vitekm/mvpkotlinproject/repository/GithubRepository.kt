package come.geekbrains.vitekm.mvpkotlinproject.repository

import come.geekbrains.vitekm.mvpkotlinproject.model.GithubUser

interface GithubRepository {

    fun getUsers(): List<GithubUser>
}