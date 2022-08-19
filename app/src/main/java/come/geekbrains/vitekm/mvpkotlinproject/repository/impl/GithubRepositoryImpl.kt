package come.geekbrains.vitekm.mvpkotlinproject.repository.impl

import come.geekbrains.vitekm.mvpkotlinproject.core.mapper.UserMapper
import come.geekbrains.vitekm.mvpkotlinproject.core.network.UsersApi
import come.geekbrains.vitekm.mvpkotlinproject.model.GithubUser
import come.geekbrains.vitekm.mvpkotlinproject.repository.GithubRepository
import io.reactivex.rxjava3.core.Single

class GithubRepositoryImpl constructor(
    private val usersApi: UsersApi
) : GithubRepository {

    override fun getUsers(): Single<List<GithubUser>> {
        return usersApi.getAllUsers()
            .map { it.map(UserMapper::mapToEntity) }

    }

    override fun getUserById(login: String): Single<GithubUser> {
        return usersApi.getUser(login)
            .map(UserMapper::mapToEntity)
    }
}
