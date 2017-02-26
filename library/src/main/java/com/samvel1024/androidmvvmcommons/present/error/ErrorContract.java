package com.samvel1024.androidmvvmcommons.present.error;

/**
 * Contract for view and view model which are handling error events
 *
 * @author Samvel Abrahamyan
 */

public interface ErrorContract {
    interface View {
        void onShowErrorMessage(String msg);
    }

    interface ViewModel {

        void onError(Throwable err);

        void onError(Throwable err, String message);
    }

}
