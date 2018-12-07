package com.zlt.test_map.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zlt.test_map.R;
import com.zlt.test_map.bean.SupplierBean;

import java.util.List;

/***
 * 供应商
 */
public class SupplierAdapter extends BaseQuickAdapter<SupplierBean.DataBean.ListBean,BaseViewHolder> {
    public SupplierAdapter(int layoutResId, @Nullable List<SupplierBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SupplierBean.DataBean.ListBean item) {
        helper.setText(R.id.city,item.getSupplier());
        helper.addOnClickListener(R.id.check);
    }
}
