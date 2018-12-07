package com.zlt.test_map.ui.activity;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zlt.test_map.R;
import com.zlt.test_map.base.BaseActivity;
import com.zlt.test_map.ui.fragment.EventSummaryFragment;
import com.zlt.test_map.ui.fragment.RealTimeManagementFragment;

import butterknife.BindView;
import butterknife.OnClick;

/***
 *应急处置
 * 新事件Item跳转————事件概况
 */

public class EventSummaryActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.event_summary_text)
    TextView eventSummaryText;
    @BindView(R.id.event_summary_line)
    View eventSummaryLine;
    @BindView(R.id.real_time_management_text)
    TextView realTimeManagementText;
    @BindView(R.id.real_time_management_line)
    View realTimeManagementLine;
    @BindView(R.id.myFrameLayout)
    FrameLayout myFrameLayout;

    @Override
    protected int setLayout() {
        addFragment(new EventSummaryFragment());
        return R.layout.activity_event_summary;
    }

    @Override
    protected void initView() {
        title.setText("事件概况");
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

    @OnClick({R.id.go_back, R.id.event_summary, R.id.real_time_management})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.go_back:
                finish();
                break;
            case R.id.event_summary:
                replaceFragment(new EventSummaryFragment());
                eventSummaryLine.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                eventSummaryText.setTextColor(getResources().getColor(R.color.colorAccent));
                realTimeManagementLine.setBackgroundColor(getResources().getColor(R.color.transparent));
                realTimeManagementText.setTextColor(getResources().getColor(R.color.black));
                title.setText("事件概况");
                break;
            case R.id.real_time_management:
                replaceFragment(new RealTimeManagementFragment());
                realTimeManagementLine.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                realTimeManagementText.setTextColor(getResources().getColor(R.color.colorAccent));
                eventSummaryLine.setBackgroundColor(getResources().getColor(R.color.transparent));
                eventSummaryText.setTextColor(getResources().getColor(R.color.black));
                title.setText("事件详情");
                break;
        }
    }
}
