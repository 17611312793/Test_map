package com.zlt.test_map.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zlt.test_map.R;
import com.zlt.test_map.ui.adapter.RoadAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by ckx on 2018/6/27.
 * ChoiceTownshipDialog
 * 点击选择乡镇item打开选择统计类型
 */
public class ChoiceStatisticalTypeDialog extends Dialog {
    @BindView(R.id.myRecyclerView)
    RecyclerView myRecyclerView;
    private Context context;


    public ChoiceStatisticalTypeDialog(final Context context) {
        super(context, R.style.RemindDialog);// 必须调用父类的构造函数
        this.context = context;
        View contentView = LayoutInflater.from(context).inflate(R.layout.layout_dialog_choice_statistical_type, null);
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
            list.add("县道统计"+i);
        }
        RoadAdapter roadAdapter = new RoadAdapter(R.layout.layout_dialog_choice_statistical_type, list);
        myRecyclerView.setAdapter(roadAdapter);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @OnClick(R.id.close)
    public void Close() {
        dismiss();
    }
}
