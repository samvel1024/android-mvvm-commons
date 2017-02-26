package com.samvel1024.android_mvvm_commons_sample;

/**
 * @author Samvel Abrahamyan
 */

public class DiContext {

    private final UserService userService;

    public DiContext() {
        this.userService = new UserService();
    }

    public UserService getUserService() {
        return userService;
    }
}
