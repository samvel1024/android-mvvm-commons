package com.samvel1024.androidmvvmcommons.present.lifecycle;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 *
 * ViewModels using RXJava async calls have to compose this class
 * and invoke addSubscriber when making an API call
 *
 * @author Samvel Abrahamyan
 */

public class RxLifecycleVM implements LifecycleVM {

    private final CompositeDisposable disposables = new CompositeDisposable();

    public CompositeDisposable getCompositeDisposable() {
        return disposables;
    }

    public void addSubscriber(Disposable disposable) {
        disposables.add(disposable);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {
        disposables.dispose();
    }
}
