package com.samvel1024.androidmvvmcommons.service.connectivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author Samvel Abrahamyan
 */

public class AndroidConnectivityService implements ConnectivityService {

    private final Context context;

    private AndroidConnectivityService(Context context) {
        this.context = context;
    }


    @Override
    public void verifyNetworkConnected() {
        if (!isNetworkConnected())
            throw new NetworkConnectionException();
    }

    @Override
    public boolean isNetworkConnected() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
