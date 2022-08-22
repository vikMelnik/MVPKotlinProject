package come.geekbrains.vitekm.mvpkotlinproject.schedulers

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class MySchedulers {

    fun main(): Scheduler = AndroidSchedulers.mainThread()

    fun io(): Scheduler = Schedulers.io()

    fun computation(): Scheduler = Schedulers.computation()

    fun start() {
        Schedulers.start()
    }

    fun shutdown() {
        Schedulers.shutdown()
    }
}