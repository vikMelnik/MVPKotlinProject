package come.geekbrains.vitekm.mvpkotlinproject

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

private val data = listOf(1, 4, 6, 98, 0, 1, 1, 4)
private val news = listOf("все хорошо!", "все плохо, но мы не виноваты")

fun main() {

    saveCompletable("che@che.com")
        .andThen(getNews())
        .onErrorResumeNext { error ->
            Log.e("CHTO_TO", error.message, error)
            getNewsFromApi()
        }
        .subscribe(
            {
                println("Single subscribe")
                println(it)
            },
            {
                println(it.message)
            })

}

private fun getMaybeService() = Maybe.create<Boolean> { emitter ->
    try {
        when (2) {
            0 -> emitter.onSuccess(false)
            1 -> emitter.onSuccess(true)
            2 -> emitter.onComplete()
        }
    } catch (e: java.lang.Exception) {
        println(e.message)
        emitter.onError(e)
    }
}

private fun getNews() = Single.create<List<String>> { emitter ->
    try {
        val result = news
        emitter.onError(IllegalArgumentException())
    } catch (e: Exception) {
        println(e.message)
        emitter.onError(e)
    }
}

private fun getNewsFromApi() = Single.create<List<String>> { emitter ->
    try {
        val result = news
        emitter.onSuccess(result)
    } catch (e: Exception) {
        println(e.message)
        emitter.onError(e)
    }
}

private fun saveCompletable(email: String) = Completable.create { emitter ->
    try {
        saveToDb(email)
        emitter.onComplete()
    } catch (e: Exception) {
        println(e.message)
        emitter.onError(e)
    }
}

private fun saveToDb(email: String) {
    Thread.sleep(1000)
    println("Saved $email")
}

private fun <T> Single<T>.subscribeByDefault(): Single<T> {
    return this
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

private fun Disposable.disposeBy(bag: CompositeDisposable) {
    bag.add(this)
}

private fun getUserInfo(name: String): Observable<List<String>> {
    return Observable.just(listOf(name, "email.com"))
}

private fun mapToListAndAdd(value: Int): List<Int> {
    return listOf(value, 8)
}
