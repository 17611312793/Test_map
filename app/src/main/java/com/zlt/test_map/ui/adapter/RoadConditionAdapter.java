package com.zlt.test_map.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zlt.test_map.R;
import com.zlt.test_map.bean.RoadConditionBean;

import java.util.List;

/***
 * 公路状况列表
 */
public class RoadConditionAdapter extends BaseQuickAdapter<RoadConditionBean.DataBean.ListBean,BaseViewHolder> {
    public RoadConditionAdapter(int layoutResId, @Nullable List<RoadConditionBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RoadConditionBean.DataBean.ListBean item) {
        helper.setText(R.id.tv_routecode,item.getRoutecode());
        helper.setText(R.id.tv_routeclaim,item.getRouteclaim());
        helper.setText(R.id.tv_mileage,item.getMileage());
        helper.setText(R.id.tv_grade,item.getGrade());
        helper.setText(R.id.tv_routetype,item.getRoutetype());
        helper.setText(R.id.tv_adminrank,item.getAdminrank());
//        Glide.with(mContext).load(item.getCoverphoto()).into((ImageView) helper.getView(R.id.img_coverphoto));
        Glide.with(mContext).load(R.mipmap.tu1).into((ImageView) helper.getView(R.id.img_coverphoto));
    }
}
