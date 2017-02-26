package com.samvel1024.androidmvvmcommons.service;

/**
 * Should be thrown when receiving not a successful HTTP status code
 *
 * @author Samvel Abrahamyan
 */

public class BadResponseException extends RuntimeException {
    public BadResponseException(String message) {
        super(message);
    }
}

