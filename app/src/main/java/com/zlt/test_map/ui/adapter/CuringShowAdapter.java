package com.zlt.test_map.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zlt.test_map.R;
import com.zlt.test_map.bean.CuringShowBean;

import java.util.ArrayList;
import java.util.List;

public class CuringShowAdapter extends BaseQuickAdapter<CuringShowBean.DataBean.ListBean, BaseViewHolder> {
    public CuringShowAdapter(int layoutResId, @Nullable List<CuringShowBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    private List<CuringShowBean.DataBean.ListBean> data = new ArrayList<>();

    public List<CuringShowBean.DataBean.ListBean> data() {
        return data;
    }

    @Override
    protected void convert(BaseViewHolder helper, CuringShowBean.DataBean.ListBean item) {
        helper.setText(R.id.tv_routecode, item.getRoutecode());
        helper.setText(R.id.tv_routeclaim, item.getRouteclaim());
        helper.setText(R.id.tv_mileage, item.getMileage());
        helper.setText(R.id.tv_grade, item.getGrade());
        helper.setText(R.id.tv_routetype, item.getRoutetype());
        helper.setText(R.id.tv_adminrank, item.getAdminrank());
//        Glide.with(mContext).load(item.getCoverphoto()).into((ImageView) helper.getView(R.id.img_coverphoto));
        Glide.with(mContext).load(R.mipmap.tu1).into((ImageView) helper.getView(R.id.img_coverphoto));
        CheckBox check = helper.getView(R.id.check);
        helper.addOnClickListener(R.id.check);

    }
}
