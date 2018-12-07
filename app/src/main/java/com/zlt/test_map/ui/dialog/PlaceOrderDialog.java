package com.zlt.test_map.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zlt.test_map.R;
import com.zlt.test_map.bean.SupplierBean;
import com.zlt.test_map.ui.activity.PendingAcceptanceTaskActivity;
import com.zlt.test_map.ui.adapter.SupplierAdapter;
import com.zlt.test_map.utile.RetrofitUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by ckx on 2018/6/27.
 * ChoiceTownshipDialog
 * 选择供应商Dialog
 */
public class PlaceOrderDialog extends Dialog {
    @BindView(R.id.myRecyclerView)
    RecyclerView myRecyclerView;
    private Context context;


    public PlaceOrderDialog(final Context context) {
        super(context, R.style.RemindDialog);// 必须调用父类的构造函数
        this.context = context;
        View contentView = LayoutInflater.from(context).inflate(R.layout.layout_dialog_place_order, null);
        setContentView(contentView);

        ButterKnife.bind(this, contentView);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.8
        dialogWindow.setAttributes(lp);
        initDate();
    }

    private void initDate() {
        RetrofitUtils.getInstance().getApiService().getSupplier()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SupplierBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error",e.getMessage());
                    }

                    @Override
                    public void onNext(SupplierBean supplierBean) {
                        int code = supplierBean.getCode();
                        if (code == 200){
                            List<SupplierBean.DataBean.ListBean> list = supplierBean.getData().getList();
                            SupplierAdapter supplierAdapter = new SupplierAdapter(R.layout.item_dialog_choice_statistical_type, list);
                            myRecyclerView.setAdapter(supplierAdapter);
                            myRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                        }
                    }
                });
    }

    @OnClick(R.id.close)
    public void Close() {
        dismiss();
    }

    @OnClick(R.id.bt_place_order)
    public void onViewClicked() {
        Intent intent = new Intent();
        intent.setClass(context, PendingAcceptanceTaskActivity.class);
        intent.putExtra("condition_id","");
        context.startActivity(intent);
        ((Activity) context).finish();
    }
}
