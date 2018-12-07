package com.zlt.test_map.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zlt.test_map.R;

import java.util.List;

public class AddAdapter extends BaseQuickAdapter<Integer,BaseViewHolder> {
    public AddAdapter(int layoutResId, @Nullable List<Integer> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
        Glide.with(mContext).load(R.mipmap.collection_add).into((ImageView) helper.getView(R.id.add));
    }
}
