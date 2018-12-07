package com.zlt.test_map.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zlt.test_map.R;
import com.zlt.test_map.bean.NewsBean;

import java.util.List;

public class NewsAdapter extends BaseQuickAdapter<NewsBean.DataBean.ListBean, BaseViewHolder> {
    public NewsAdapter(int layoutResId, @Nullable List<NewsBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsBean.DataBean.ListBean item) {
        Glide.with(mContext).load(item.getSceneimg()).into((ImageView) helper.getView(R.id.img_sceneimg));
        helper.setText(R.id.tv_title,item.getTitle());
        helper.setText(R.id.tv_site,item.getSite());
        helper.setText(R.id.tv_conditions,item.getConditions());
    }
}
