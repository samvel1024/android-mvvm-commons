package com.samvel1024.androidmvvmcommons.service.connectivity;

/**
 * @author Samvel Abrahamyan
 */

public interface ConnectivityService {

    /**
     * Should throw NetworkConnectionException when isNetworkConnected is false
     */
    void verifyNetworkConnected();

    boolean isNetworkConnected();

}

