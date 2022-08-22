package come.geekbrains.vitekm.mvpkotlinproject.model

import come.geekbrains.vitekm.mvpkotlinproject.allinterface.IDataApi
import come.geekbrains.vitekm.mvpkotlinproject.allinterface.IUserRepo
import come.geekbrains.vitekm.mvpkotlinproject.schedulers.MySchedulersFactory
import io.reactivex.rxjava3.core.Single


class RetrofitUserRepo (val api: IDataApi) : IUserRepo {
    override fun getUsers(): Single<List<GithubUser>> {
        return api.getUsers().subscribeOn(MySchedulersFactory.create().io())
    }

    override fun getUserByLogin(login: String): Single<GithubUserNew> {
        return api.getUserByLogin(login).subscribeOn(MySchedulersFactory.create().io())
    }

    override fun getUserRepos(
        login: String,
        type: String?,
        sort: String?,
        direction: String?,
        perPage: Int?,
        page: Int?
    ): Single<List<Repository>> {
        return api.getUserRepos(login, type, sort, direction, perPage, page)
            .subscribeOn(MySchedulersFactory.create().io())
    }

    override fun getRepositoryByUrl(url: String): Single<Repository> {
        return api.getRepositoryByUrl(url).subscribeOn(MySchedulersFactory.create().io())
    }
}