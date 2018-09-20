/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 20.09.18 17:56
 */

package main

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.NewThreadScheduler
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit


object Disposable {
  @JvmStatic
  fun main(args: Array<String>) {
    val publishSubject = PublishSubject.create<Long>()
    val disposable = publishSubject
        .doOnNext { println("next") }
        .subscribe { println("subscribe") }
    Observable.interval(1, TimeUnit.SECONDS)
        .forEach {
          if (it == 5L) disposable.dispose()
          publishSubject.onNext(it)
        }
    while (true) {
    }
  }
}
