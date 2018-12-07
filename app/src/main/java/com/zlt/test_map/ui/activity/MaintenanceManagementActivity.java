package com.zlt.test_map.ui.activity;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zlt.test_map.R;
import com.zlt.test_map.base.BaseActivity;
import com.zlt.test_map.ui.fragment.HarmFragment;
import com.zlt.test_map.ui.fragment.RouteFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 养护管理
 */
public class MaintenanceManagementActivity extends BaseActivity {

    @BindView(R.id.route_text)
    TextView routeText;
    @BindView(R.id.route_line)
    View routeLine;
    @BindView(R.id.harm_text)
    TextView harmText;
    @BindView(R.id.harm_line)
    View harmLine;
    @BindView(R.id.myFrameLayout)
    FrameLayout myFrameLayout;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.all_choose)
    public TextView allChoose;

    @Override
    protected int setLayout() {
        addFragment(new RouteFragment());
        return R.layout.activity_maintenanceanagement;
    }

    @Override
    protected void initView() {
        title.setText("养护管理");
    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.myFrameLayout, fragment).commit();
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.myFrameLayout, fragment).commit();
    }

    @Override
    protected void initData() {
    }


    @OnClick({R.id.route, R.id.harm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.route:
                replaceFragment(new RouteFragment());
                routeLine.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                routeText.setTextColor(getResources().getColor(R.color.colorAccent));
                harmLine.setBackgroundColor(getResources().getColor(R.color.transparent));
                harmText.setTextColor(getResources().getColor(R.color.black));
                allChoose.setVisibility(View.VISIBLE);
                title.setText("养护管理");
                break;
            case R.id.harm:
                replaceFragment(new HarmFragment());
                harmLine.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                harmText.setTextColor(getResources().getColor(R.color.colorAccent));
                routeLine.setBackgroundColor(getResources().getColor(R.color.transparent));
                routeText.setTextColor(getResources().getColor(R.color.black));
                allChoose.setVisibility(View.GONE);
                title.setText("选择病害");
                break;
        }
    }

    @OnClick(R.id.go_back)
    public void GoBack() {
        finish();
    }
}
