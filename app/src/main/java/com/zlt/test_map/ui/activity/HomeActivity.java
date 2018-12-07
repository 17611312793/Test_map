package com.zlt.test_map.ui.activity;

import android.content.Intent;
import android.view.View;

import com.zlt.test_map.R;
import com.zlt.test_map.base.BaseActivity;

import butterknife.OnClick;

/***
 * 首页
 */

public class HomeActivity extends BaseActivity {

    @Override
    protected int setLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.ome_icon, R.id.home_login, R.id.home_map, R.id.home_collection, R.id.home_maintenance, R.id.home_emergency})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ome_icon://头像
                break;
            case R.id.home_login://登陆and注册
                break;
            case R.id.home_map://电子地图
                startActivity(new Intent(this, MapActivity.class));
                break;
            case R.id.home_collection://路况采集
                startActivity(new Intent(this, RoadConditionCollectionActivity.class));
                break;
            case R.id.home_maintenance://养护管理
                startActivity(new Intent(this, MaintenanceManagementActivity.class));
                break;
            case R.id.home_emergency://应急处理
                startActivity(new Intent(this, EmergencyDisposalActivity.class));
                break;
        }
    }
}
