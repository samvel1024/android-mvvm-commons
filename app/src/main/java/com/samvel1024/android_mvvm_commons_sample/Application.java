package com.samvel1024.android_mvvm_commons_sample;

import com.samvel1024.androidmvvmcommons.di.DiContextAware;

/**
 * @author Samvel Abrahamyan
 */

public class Application extends android.app.Application implements DiContextAware<DiContext>{

    private DiContext diContext;

    @Override
    public void onCreate() {
        super.onCreate();
        this.diContext = new DiContext();
    }

    @Override
    public DiContext getDiContext() {
        return diContext;
    }
}
