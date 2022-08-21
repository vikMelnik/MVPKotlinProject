package come.geekbrains.vitekm.mvpkotlinproject.core.network

import com.google.gson.GsonBuilder
import come.geekbrains.vitekm.mvpkotlinproject.BuildConfig
import come.geekbrains.vitekm.mvpkotlinproject.allinterface.UsersApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkProvider {

    val usersApi by lazy { createRetrofit().create(UsersApi::class.java) }


    private fun createGson() = GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    private fun createRetrofit() = Retrofit.Builder()
        .client(createClient())
        .baseUrl(BuildConfig.SERVER_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun createClient() = OkHttpClient
        .Builder()
        .build()

}