package me.kevincampos.domain.interactor

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import me.kevincampos.domain.executor.PostExecutionThread

abstract class ObservableUseCase <T, in Params> constructor(
        private val postExecutionThread: PostExecutionThread
) {

    private val disposables = CompositeDisposable()

    protected abstract fun buildUseCaseObservable(params: Params? = null) : Observable<T>

    open fun execute(observer: DisposableObserver<T>, params: Params? = null) {
        val observable = buildUseCaseObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(postExecutionThread.scheduler)
        addDisposable(observable.subscribeWith(observer))
    }

    fun dispose() {
        disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

}