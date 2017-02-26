package com.samvel1024.androidmvvmcommons.present.lifecycle;

/**
 * All ViewModels which have any subscribe/unsubscribe logic
 * or need to make use of lifecycle hooks of
 * the hosting view component have to implement this interface
 *
 * @author Samvel Abrahamyan
 */
public interface LifecycleVM {

    void onStart();

    void onDestroy();

}