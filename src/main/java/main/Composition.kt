package main

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.internal.schedulers.NewThreadScheduler
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject


object Composition {
    val subject = PublishSubject.create<Int>()
    val thread1 = NewThreadScheduler()
    val thread2 = NewThreadScheduler()
    val thread3 = NewThreadScheduler()
    @JvmStatic
    fun main(args: Array<String>) {
        subject
                .compose (backgroundWrapprer())
                .flatMap { Observable.just(it).doOnNext { if (it == 2) throw Exception() }.onErrorReturn { 0 } }
                .subscribe({ print(it) }, {
                    it.printStackTrace()
                })
        while (true) {
        }
    }

    private fun <T> backgroundWrapprer(): ObservableTransformer<T, T> {
        return ObservableTransformer { it.observeOn(Schedulers.computation()).subscribeOn(thread2) }
    }

}
