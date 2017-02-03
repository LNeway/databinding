package com.devtom.abinding.databinding;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.devtom.abinding.R;

/**
 * Created by tom on 2016/12/22.
 */

public abstract class DataBindingActivity<T extends ViewDataBinding> extends FragmentActivity {

    protected T dataBinding;
    private FrameLayout frameLayout;
    private View contentView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        frameLayout = (FrameLayout) LayoutInflater.from(this).inflate(
                R.layout.base_data_binding_view_container,
                (ViewGroup) getWindow().getDecorView().getRootView(), false);
        super.setContentView(frameLayout); // 这里需要调用父类未覆盖的方法来保证View 被添加到窗口当中
        int layoutId = getLayoutId();
        if(layoutId < 0) {
            throw new IllegalArgumentException("please check the layout file!");
        }
        setContentView(layoutId);
        dataBinding = DataBindingUtil.bind(contentView);
    }

    public abstract int getLayoutId();

    @Override
    public void setContentView(int layoutResID) {
        setContentView(LayoutInflater.from(this).inflate(layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        setContentView(view, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        contentView = view;
        frameLayout.removeAllViews();
        frameLayout.addView(view, params);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }
}
