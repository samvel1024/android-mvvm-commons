package com.samvel1024.androidmvvmcommons.present.progress;

/**
 * Views which have a generic progress dialog have to implement this contract
 *
 * @author Samvel Abrahamyan
 */

public interface ProgressIndicatorView {

    void onShowProgress();

    void onHideProgress();

}
