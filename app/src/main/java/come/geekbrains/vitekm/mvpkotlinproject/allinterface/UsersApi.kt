package come.geekbrains.vitekm.mvpkotlinproject.allinterface

import come.geekbrains.vitekm.mvpkotlinproject.core.network.UserDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersApi : IDataApi {

    @GET("/users")
    fun getAllUsers(): Single<List<UserDto>>

    @GET("/users/{login}")
    fun getUser(@Path("login") login: String): Single<UserDto>

}