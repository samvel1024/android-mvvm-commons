package com.samvel1024.androidmvvmcommons.present.fragment;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragments using data binding should extend this class.
 * Takes the responsibility of initializing the binding object
 * based on the provided layout resource id
 *
 * @author Samvel Abrahamyan
 */

public abstract class BindingFragment<VDB extends ViewDataBinding> extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        VDB binding = DataBindingUtil.inflate(
                inflater,
                getLayoutResource(),
                container,
                false
        );
        onCreateBinding(binding);
        return binding.getRoot();
    }

    /**
     * Subclasses have to initialize the binding object
     * and set all the required variables declared in the XML
     *
     * @param binding binding object
     */
    protected abstract void onCreateBinding(VDB binding);

    /**
     * @return the xml layout resource
     */
    @LayoutRes
    protected abstract int getLayoutResource();

}
