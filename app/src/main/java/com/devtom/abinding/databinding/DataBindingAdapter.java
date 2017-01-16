package com.devtom.abinding.databinding;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.CallSuper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 2016/12/22.
 */

public abstract class DataBindingAdapter<T> extends BaseAdapter {

    protected List<T> datas;
    private Context context;

    public DataBindingAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<T> data) {
        if (datas != null) {
            datas.clear();
        } else {
            datas = new ArrayList();
        }
        datas.addAll(data);
    }

    public void append(T data) {
        if(datas == null) {
            datas = new ArrayList();
        }
        datas.add(data);
    }

    public List<T> getData() {
        return datas;
    }

    public void clear() {
        if(datas != null) {
            datas.clear();
        }
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public T getItem(int position) {
        if (datas == null || position >= datas.size()) {
            return null;
        }
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = getViewHolder();
            convertView = createView(context, parent, getItemViewType(position));
            viewHolder.onCreate(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.onBind(getItem(position));
        return convertView;
    }

    public abstract ViewHolder getViewHolder();

    public abstract View createView(Context context, ViewGroup parent, int viewType);

    public abstract static class ViewHolder<T, DataBindingType extends ViewDataBinding> {

        protected DataBindingType binding;

        @CallSuper
        protected void onCreate(View convertView) {
            binding = DataBindingUtil.bind(convertView);
        }

        public abstract void onBind(T data);
    }
}
