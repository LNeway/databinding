package com.devtom.abinding.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tom on 2017/1/5.
 */

public abstract class DataBindingFragment<DataBinding extends ViewDataBinding> extends Fragment {

    protected DataBinding dataBinding;

    public abstract @LayoutRes int getLayoutId();

    @CallSuper
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        dataBinding = DataBindingUtil.bind(view);
        return view;
    }
}
