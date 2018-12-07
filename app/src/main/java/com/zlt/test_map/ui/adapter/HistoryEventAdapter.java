package com.zlt.test_map.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zlt.test_map.R;
import com.zlt.test_map.bean.HistoryBean;

import java.util.List;

public class HistoryEventAdapter extends BaseQuickAdapter<HistoryBean.DataBean.ListBean, BaseViewHolder> {
    public HistoryEventAdapter(int layoutResId, @Nullable List<HistoryBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryBean.DataBean.ListBean item) {
        helper.setText(R.id.city, item.getEvent());
        helper.setText(R.id.tv_event_type, item.getEvent_type());
        helper.setText(R.id.tv_starttime, item.getRegdate());
        helper.setText(R.id.tv_people, item.getParticipant());
        helper.setText(R.id.tv_peoples, item.getLeader());
        helper.setText(R.id.tv_number, item.getMaterial());
    }
}
