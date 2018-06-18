package main

import io.reactivex.Observable
import io.reactivex.ObservableSource

object Mappers {
    @JvmStatic
    fun main(args: Array<String>) {
        Observable.just(1, 2, 3, 4, 4, 4, 4, 4, 4)
                .concatMappers({ Observable.error<String>(Exception()) },
                        { Observable.just("").doOnNext { print("dsffs") } }
                )
                .doOnComplete { print("onComplete") }
                .subscribe({ print("done")},{ print("error")})
        while(true){}
    }
}

fun <I : Any, R : Any> Observable<I>.concatMappers(vararg mappers: (I) -> ObservableSource<R>): Observable<R> = concatMap { item -> Observable.concat(mappers.map { it(item) }) }
