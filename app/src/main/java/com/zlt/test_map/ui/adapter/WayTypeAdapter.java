package com.zlt.test_map.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zlt.test_map.R;
import com.zlt.test_map.bean.WayTypeBean;

import java.util.List;

public class WayTypeAdapter extends BaseQuickAdapter<WayTypeBean.DataBean.ListBean,BaseViewHolder> {
    public WayTypeAdapter(int layoutResId, @Nullable List<WayTypeBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WayTypeBean.DataBean.ListBean item) {
        helper.setText(R.id.city,item.getType());
    }
}
