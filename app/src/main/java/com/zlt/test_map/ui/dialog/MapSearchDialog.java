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
 *  地图搜索Dialog
 */
public class MapSearchDialog extends Dialog {
    @BindView(R.id.myRecyclerView)
    RecyclerView myRecyclerView;
    private Context context;


    public MapSearchDialog(final Context context) {
        super(context, R.style.RemindDialog);// 必须调用父类的构造函数
        this.context = context;
        View contentView = LayoutInflater.from(context).inflate(R.layout.layout_dialog_map_search, null);
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
            list.add("大路镇"+i);
        }
        RoadAdapter roadAdapter = new RoadAdapter(R.layout.item_dialog_map_search, list);
        myRecyclerView.setAdapter(roadAdapter);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @OnClick(R.id.close)
    public void Close() {
        dismiss();
    }
}
