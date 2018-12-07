package com.zlt.test_map.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import com.zlt.test_map.R;
import com.zlt.test_map.base.BaseActivity;
import com.zlt.test_map.ui.adapter.RoadAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/***
 * 琼海市路线情况表
 */

public class RouteableActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.myRecyclerView)
    RecyclerView myRecyclerView;

    @Override
    protected int setLayout() {
        return R.layout.activity_routeable;
    }

    @Override
    protected void initView() {
        title.setText("琼海市路线情况表");
    }

    @Override
    protected void initData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("熟里-社昌（C043）");
        }
        RoadAdapter roadAdapter = new RoadAdapter(R.layout.item_routeable, list);
        myRecyclerView.setAdapter(roadAdapter);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @OnClick(R.id.go_back)
    public void GoBack() {
        finish();
    }
}
