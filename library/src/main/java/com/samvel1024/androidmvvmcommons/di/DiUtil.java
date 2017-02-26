package com.samvel1024.androidmvvmcommons.di;

import android.app.Activity;
import android.content.Context;

/**
 *
 * Gets the dependency injection container from
 * the scope of a UI components
 *
 * @author Samvel Abrahamyan
 */

public class DiUtil {

    public static <T> T from (android.support.v4.app.Fragment fragment){
        return from(fragment.getActivity());
    }

    public static <T> T from (android.app.Fragment fragment){
        return from(fragment.getActivity());
    }

    public static <T> T from(Activity activity) {
        return from(activity.getApplicationContext());
    }

    @SuppressWarnings("unchecked")
    private static <T> T from(Context context){
        DiContextAware<T> diContextProvider;
        try{
            diContextProvider = (DiContextAware<T>) context.getApplicationContext();
        }catch (Exception e){
            throw new IllegalStateException("Application class should implement DiContextAware", e);
        }
        return diContextProvider.getDiContext();
    }


    private DiUtil(){
        throw new UnsupportedOperationException();
    }

}
