package com.zk.android_utils.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zk.android_utils.R;
import com.zk.android_utils.base.FullScreenDialogFragment;

/**
 * author: ZK.
 * date:   On 2018-06-28.
 */
public class LoadingFragment extends FullScreenDialogFragment {

    private static final String TAG = "LOADINGFRAGMENT";

    private static LoadingFragment instance;
    private static FragmentManager fragmentManager;

    public static LoadingFragment getInstance(FragmentManager manager) {
        fragmentManager = manager;
        instance = (LoadingFragment) fragmentManager.findFragmentByTag(TAG);
        if (instance == null)
            instance = new LoadingFragment();
        return instance;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loading, null);
        setCancelable(false);
        return view;
    }


    public void show() {
        if (fragmentManager != null && !isAdded())
            show(fragmentManager, getClass().getName());
    }

    @Override
    public void dismissAllowingStateLoss() {
        super.dismissAllowingStateLoss();

    }
}
