package com.zlt.test_map.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zlt.test_map.R;
import com.zlt.test_map.bean.StartAcceptanceBean;

import java.util.List;

public class StartAcceptanceListaAdapter extends BaseQuickAdapter<StartAcceptanceBean.DataBean.ListaBean, BaseViewHolder> {
    public StartAcceptanceListaAdapter(int layoutResId, @Nullable List<StartAcceptanceBean.DataBean.ListaBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StartAcceptanceBean.DataBean.ListaBean item) {
        helper.setText(R.id.tv_routecode, item.getRoutecode());
        helper.setText(R.id.tv_routeclaim, item.getRouteclaim());
        helper.setText(R.id.tv_ingnum, item.getIngnum());
        helper.setText(R.id.tv_position, item.getPosition());
        helper.setText(R.id.tv_type, item.getType());
        helper.setText(R.id.tv_area, item.getArea());
//        Glide.with(mContext).load(item.getImages()).into((ImageView) helper.getView(R.id.images));
        Glide.with(mContext).load(R.mipmap.tu1).into((ImageView) helper.getView(R.id.images1));
    }
}
