package com.zlt.test_map.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zlt.test_map.R;
import com.zlt.test_map.bean.TobereceivedBean;

import java.util.List;

public class TobereceivedAdapter extends BaseQuickAdapter<TobereceivedBean.DataBean.ListBean, BaseViewHolder> {
    public TobereceivedAdapter(int layoutResId, @Nullable List<TobereceivedBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TobereceivedBean.DataBean.ListBean item) {
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