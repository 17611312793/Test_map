package com.zlt.test_map.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zlt.test_map.R;
import com.zlt.test_map.bean.StartAcceptanceBean;

import java.util.List;

public class StartAcceptanceListbAdapter extends BaseQuickAdapter<StartAcceptanceBean.DataBean.ListbBean, BaseViewHolder> {
    public StartAcceptanceListbAdapter(int layoutResId, @Nullable List<StartAcceptanceBean.DataBean.ListbBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StartAcceptanceBean.DataBean.ListbBean item) {

//        Glide.with(mContext).load(item.getImages()).into((ImageView) helper.getView(R.id.images));
        Glide.with(mContext).load(R.mipmap.tu2).into((ImageView) helper.getView(R.id.images1));
        helper.setText(R.id.backup,item.getBackup());
    }
}
