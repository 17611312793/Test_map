package com.zlt.test_map.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zlt.test_map.R;
import com.zlt.test_map.bean.PendingaccBean;

import java.util.List;

public class PendingaccAdapter extends BaseQuickAdapter<PendingaccBean.DataBean.ListBean, BaseViewHolder> {
    public PendingaccAdapter(int layoutResId, @Nullable List<PendingaccBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PendingaccBean.DataBean.ListBean item) {
        helper.setText(R.id.tv_routecode,item.getRoutecode());
        helper.setText(R.id.tv_routeclaim,item.getRouteclaim());
        helper.setText(R.id.tv_ingnum,item.getIngnum());
        helper.setText(R.id.tv_position,item.getPosition());
        helper.setText(R.id.tv_type,item.getType());
        helper.setText(R.id.tv_area,item.getArea());
//        Glide.with(mContext).load(item.getImages()).into((ImageView) helper.getView(R.id.img_images));
        Glide.with(mContext).load(R.mipmap.tu1).into((ImageView) helper.getView(R.id.img_images));
    }
}