package com.samvel1024.androidmvvmcommons.present.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.samvel1024.androidmvvmcommons.R;

import org.jetbrains.annotations.NotNull;

/**
 * A generic activity which is going to hold a single fragment
 * and replace dummy activities with no logic. Should be used
 * only when there is no any activity <-> fragment interaction
 *
 * @author Samvel Abrahamyan
 */

public class FragmentHolderActivity extends AppCompatActivity {

    static final String FRAGMENT_TAG = "my_lonely_fragment";

    private static final String FRAGMENT_CLASS_NAME = "fragment_class";
    private static final String FRAGMENT_ARGS = "fragment_args";


    public static void launch(
            Context context,
            @NotNull Class<? extends Fragment> fragmentClass) {
        launch(context, fragmentClass, new Bundle());
    }

    public static void launch(
            Context context,
            @NotNull Class<? extends Fragment> fragmentClass,
            @NotNull Bundle fragmentArgs) {
        Intent intent = new Intent(context, FragmentHolderActivity.class);
        intent.putExtra(FRAGMENT_ARGS, fragmentArgs);
        intent.putExtra(FRAGMENT_CLASS_NAME, fragmentClass.getName());
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_holder);

        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentByTag(FRAGMENT_TAG) == null) {
            Bundle fragmentArgs = getIntent().getBundleExtra(FRAGMENT_ARGS);
            String fragmentClassName = getIntent().getStringExtra(FRAGMENT_CLASS_NAME);

            FragmentTransaction transaction = fm.beginTransaction();
            Fragment fragment = Fragment.instantiate(this, fragmentClassName, fragmentArgs);
            transaction.replace(R.id.fragment_holder, fragment, FRAGMENT_ARGS);
            transaction.commit();
        }

    }

}
