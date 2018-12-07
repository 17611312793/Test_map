package com.zlt.test_map.ui.activity;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zlt.test_map.R;
import com.zlt.test_map.base.BaseActivity;
import com.zlt.test_map.ui.fragment.HistoryEventFragment;
import com.zlt.test_map.ui.fragment.NewEventFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 应急处置
 */

public class EmergencyDisposalActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.Continue)
    TextView Continue;
    @BindView(R.id.new_event_text)
    TextView newEventText;
    @BindView(R.id.new_event_line)
    View newEventLine;
    @BindView(R.id.history_event_text)
    TextView historyEventText;
    @BindView(R.id.history_event_line)
    View historyEventLine;
    @BindView(R.id.myFrameLayout)
    FrameLayout myFrameLayout;

    @Override
    protected int setLayout() {
        addFragment(new NewEventFragment());
        return R.layout.activity_emergency_disposal;
    }

    @Override
    protected void initView() {
        title.setText("应急处置");
    }

    @Override
    protected void initData() {

    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.myFrameLayout, fragment).commit();
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.myFrameLayout, fragment).commit();
    }

    @OnClick({R.id.go_back, R.id.new_event, R.id.history_event})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.go_back:
                finish();
                break;
            case R.id.new_event:
                replaceFragment(new NewEventFragment());
                newEventLine.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                newEventText.setTextColor(getResources().getColor(R.color.colorAccent));
                historyEventLine.setBackgroundColor(getResources().getColor(R.color.transparent));
                historyEventText.setTextColor(getResources().getColor(R.color.black));
                break;
            case R.id.history_event:
                replaceFragment(new HistoryEventFragment());
//                startActivity(new Intent(this, HistoryEventDetailsActivity.class));
                historyEventLine.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                historyEventText.setTextColor(getResources().getColor(R.color.colorAccent));
                newEventLine.setBackgroundColor(getResources().getColor(R.color.transparent));
                newEventText.setTextColor(getResources().getColor(R.color.black));
                break;
        }
    }
}
