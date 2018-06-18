package main

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.internal.schedulers.NewThreadScheduler
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject


object OnError {
    @JvmStatic
    fun main(args: Array<String>) {
        Observable.just(1, 2, 3,4,4,4,4,4,4)
                .flatMap { Observable.just( it).doOnNext { if (it == 2) throw Exception()  }.onErrorReturn { 0 } }
                .subscribe({ print(it) }, {
                    it.printStackTrace()
                })
        while (true){}
    }
}
