package com.zlt.test_map.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zlt.test_map.R;
import com.zlt.test_map.ui.adapter.RoadAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by ckx on 2018/6/27.
 * ChoiceTownshipDialog
 * 汇总——选择乡镇Dialog
 */
public class ChoiceTownshipDialog extends Dialog {
    @BindView(R.id.myRecyclerView)
    RecyclerView myRecyclerView;
    private Context context;


    public ChoiceTownshipDialog(final Context context) {
        super(context, R.style.RemindDialog);// 必须调用父类的构造函数
        this.context = context;
        View contentView = LayoutInflater.from(context).inflate(R.layout.layout_dialog_choice_township, null);
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
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("全市"+i);
        }
        RoadAdapter roadAdapter = new RoadAdapter(R.layout.item_dialog_choice_township, list);
        myRecyclerView.setAdapter(roadAdapter);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        roadAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                View inflate = LayoutInflater.from(context).inflate(R.layout.layout_dialog_choice_statistical_type, null);
                PopupWindow popupWindow = new PopupWindow(inflate,LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    list.add("县道统计"+i);
                }
                RoadAdapter roadAdapter = new RoadAdapter(R.layout.item_dialog_choice_statistical_type, list);
                RecyclerView myRecy = inflate.findViewById(R.id.myRecyclerView);
                myRecy.setAdapter(roadAdapter);
                myRecy.setLayoutManager(new LinearLayoutManager(context));
                popupWindow.showAtLocation(inflate,Gravity.RIGHT,0,0);
            }
        });
    }

    @OnClick(R.id.close)
    public void Close() {
        dismiss();
    }
}
