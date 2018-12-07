package com.zlt.test_map.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zlt.test_map.R;
import com.zlt.test_map.bean.ChooseTownBean;

import java.util.List;

public class ChooseTownAdapter extends BaseQuickAdapter<ChooseTownBean.DataBean.ListBean,BaseViewHolder> {
    public ChooseTownAdapter(int layoutResId, @Nullable List<ChooseTownBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChooseTownBean.DataBean.ListBean item) {
        helper.setText(R.id.city,item.getTown());
    }
}
