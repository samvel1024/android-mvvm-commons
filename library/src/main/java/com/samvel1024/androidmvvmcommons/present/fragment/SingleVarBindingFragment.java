package com.samvel1024.androidmvvmcommons.present.fragment;

import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;

import com.samvel1024.androidmvvmcommons.present.lifecycle.LifecycleVM;


/**
 * Fragments which have only one view model variable to bind to the XML
 * have to extends this class
 *
 * VM denotes the type of the view model
 * VDB denotes the type of the binding object for the corresponding XML file
 *
 * @author Samvel Abrahamyan
 */

public abstract class SingleVarBindingFragment<VM, VDB extends ViewDataBinding> extends BindingFragment<VDB> {

    protected VM viewModel;
    protected VDB binding;
    @Nullable
    private LifecycleVM lifecycleAwareVM;


    @Override
    protected void onCreateBinding(VDB binding) {
        this.binding = binding;
        this.viewModel = onCreateVM();
        binding.setVariable(getBindingVarId(), viewModel);
        lifecycleAwareVM = (viewModel instanceof LifecycleVM) ? (LifecycleVM) viewModel : null;
        onInitView();
    }

    @Override
    protected int getLayoutResource() {
        return 0;
    }

    /**
     * @return the binding variable id like BR.viewModel
     */
    protected abstract int getBindingVarId();

    /**
     * @return the view model which is going to be bound to the xml
     */
    protected abstract VM onCreateVM();

    /**
     * Override this method and write any view initialization logic in it
     */
    protected void onInitView() {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (lifecycleAwareVM != null)
            lifecycleAwareVM.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (lifecycleAwareVM != null)
            lifecycleAwareVM.onDestroy();
    }
}
