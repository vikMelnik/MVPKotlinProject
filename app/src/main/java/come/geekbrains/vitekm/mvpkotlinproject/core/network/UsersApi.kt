package come.geekbrains.vitekm.mvpkotlinproject.core.network

import come.geekbrains.vitekm.mvpkotlinproject.model.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersApi {


    @GET("/users")
    fun getAllUsers(): Single<List<UserDto>>

    @GET("/users/{login}")
    fun getUser(@Path("login") login: String): Single<UserDto>

}