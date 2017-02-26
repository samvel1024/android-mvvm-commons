package com.samvel1024.androidmvvmcommons.present.error;

/**
 * @author Samvel Abrahamyan
 */

import android.util.Log;

import com.samvel1024.androidmvvmcommons.service.connectivity.NetworkConnectionException;

import java.util.HashSet;
import java.util.Set;

/**
 * Handles the error and shows appropriate error message.
 * For specific types of Throwables shows default error messages on the UI
 *
 * example usage
 * new ErrorContract.ErrorVM(view).onError(err);
 * or
 * ErrorVM.onError(view, err);
 *
 */

public class ErrorVM implements ErrorContract.ViewModel {


    private static final Set<Class<? extends Throwable>> notLoggableErrors;

    static {
        notLoggableErrors = new HashSet<>();
        notLoggableErrors.add(NetworkConnectionException.class);
    }

    private final ErrorContract.View view;

    public ErrorVM(ErrorContract.View view) {
        this.view = view;
    }

    /**
     *
     * May be overridden to provide new types of throwables which should not be logged
     *
     * @return set of types of Throwables which should not be logged with Log.e
     */
    protected Set<Class<? extends Throwable>> getNotLoggableErrors(){
        return notLoggableErrors;
    }

    @Override
    public void onError(Throwable err) {
        onError(err, "Something went wrong");
    }

    public void onError(Throwable err, String message) {
        if (!getNotLoggableErrors().contains(err.getClass()))
            Log.e("ErrorVM", String.valueOf(err.getMessage()), err);
        view.onShowErrorMessage(mapToUiErrorMessage(err, message));
    }

    protected String mapToUiErrorMessage(Throwable err, String defaultMessage) {
        if (err instanceof NetworkConnectionException) {
            return "No internet connection";
        }
        return defaultMessage;
    }

}
