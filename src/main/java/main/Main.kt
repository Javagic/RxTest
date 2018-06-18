package main

import io.reactivex.internal.schedulers.NewThreadScheduler
import io.reactivex.subjects.PublishSubject

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val publisher = PublishSubject.create<Int>()
        publisher.observeOn(NewThreadScheduler())
                .map {
                    while (true) {
                        println(it)
                    }
                }
                .doOnNext { println(it) }
                .doOnNext { println(it) }
                .doOnNext { println(it) }
                .doOnError(Throwable::printStackTrace)
                .subscribe({ }, { it?.printStackTrace() })

        while (true) {
            publisher.onNext(2)
        }
    }
}
