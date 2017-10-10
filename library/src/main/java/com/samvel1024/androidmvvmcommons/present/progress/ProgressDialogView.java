package com.samvel1024.androidmvvmcommons.present.progress;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

/**
 *
 * Implementation of progress dialog show and hide methods
 *
 * To use this class views (fragments, activities), have to implement
 * ProgressDialogIndicatorView and compose this implementation as a
 * private field, and then delegate all the show hide methods
 *
 * @author Samvel Abrahamyan
 */
public class ProgressDialogView implements ProgressIndicatorView {

    private final String message;
    private ProgressDialog progressDialog;
    private final Context context;

    public ProgressDialogView(Context context, String message) {
        this.message = message;
        this.context = context;
    }

    @Override
    public void onShowProgress() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(message);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    @Override
    public void onHideProgress() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }catch (IllegalArgumentException e){
            Log.e("ProgressDialogView", "Could not dismiss dialog correctly", e);
        }
    }
}
