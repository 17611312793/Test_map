package com.zlt.test_map.ui.activity;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zlt.test_map.R;
import com.zlt.test_map.base.BaseActivity;
import com.zlt.test_map.ui.fragment.DiseaseInformationFragment;
import com.zlt.test_map.ui.fragment.MaintenanceInformationFragment;

import butterknife.BindView;
import butterknife.OnClick;

/***
 * 待验收任务————TabLayout
 */

public class PendingAcceptancTesk02Activity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.disease_information_text)
    TextView diseaseInformationText;
    @BindView(R.id.disease_information_line)
    View diseaseInformationLine;
    @BindView(R.id.maintenance_information_text)
    TextView maintenanceInformationText;
    @BindView(R.id.maintenance_information_line)
    View maintenanceInformationLine;
    @BindView(R.id.myFrameLayout)
    FrameLayout myFrameLayout;

    @Override
    protected int setLayout() {
        addFragment(new DiseaseInformationFragment());
        return R.layout.activity_pending_acceptanc_tesk02;
    }

    @Override
    protected void initView() {
        title.setText("待验收任务");

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


    @OnClick({R.id.go_back, R.id.disease_information, R.id.maintenance_information})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.go_back:
                finish();
                break;
            case R.id.disease_information:
                replaceFragment(new DiseaseInformationFragment());
                diseaseInformationLine.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                diseaseInformationText.setTextColor(getResources().getColor(R.color.colorAccent));
                maintenanceInformationLine.setBackgroundColor(getResources().getColor(R.color.transparent));
                maintenanceInformationText.setTextColor(getResources().getColor(R.color.black));
                break;
            case R.id.maintenance_information:
                replaceFragment(new MaintenanceInformationFragment());
                maintenanceInformationLine.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                maintenanceInformationText.setTextColor(getResources().getColor(R.color.colorAccent));
                diseaseInformationLine.setBackgroundColor(getResources().getColor(R.color.transparent));
                diseaseInformationText.setTextColor(getResources().getColor(R.color.black));
                break;
        }
    }
}
