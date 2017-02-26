package com.samvel1024.androidmvvmcommons.service;

/**
 *
 * Used for mapping POJOs back and forth.
 * Is handy when using with an ORM for implementing
 * the mapping between ORM dependent model and the entity
 * class used throughout the application
 *
 * @author Samvel Abrahamyan
 */

public interface BeanMapper<S, T> {
    T mapRight(S source);

    S mapLeft(T source);
}