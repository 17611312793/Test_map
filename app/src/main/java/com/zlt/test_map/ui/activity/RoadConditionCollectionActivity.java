package com.zlt.test_map.ui.activity;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zlt.test_map.R;
import com.zlt.test_map.base.BaseActivity;
import com.zlt.test_map.ui.fragment.Highway_ConditionFragment;
import com.zlt.test_map.ui.fragment.RoadConditionFragment;

import butterknife.BindView;
import butterknife.OnClick;

/***
 * 公路状况
 */

public class RoadConditionCollectionActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.collection_line)
    View collectionLine;
    @BindView(R.id.collection_text)
    TextView collectionText;
    @BindView(R.id.query_line)
    View queryLine;
    @BindView(R.id.query_text)
    TextView queryText;
    @BindView(R.id.myFrameLayout)
    FrameLayout myFrameLayout;

    @Override
    protected int setLayout() {
        return R.layout.activity_road_condition_collection;
    }

    @Override
    protected void initView() {
        title.setText("公路状况");
        addFragment(new Highway_ConditionFragment());

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

    @OnClick({R.id.go_back})
    public void GoBack() {
        finish();
    }

    @OnClick({R.id.collection, R.id.query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.collection:
                collectionLine.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                queryLine.setBackgroundColor(getResources().getColor(R.color.transparent));
                collectionText.setTextColor(getResources().getColor(R.color.colorAccent));
                queryText.setTextColor(getResources().getColor(R.color.black));
                replaceFragment(new Highway_ConditionFragment());
                title.setText("公路状况");
                break;
            case R.id.query:
                collectionLine.setBackgroundColor(getResources().getColor(R.color.transparent));
                queryLine.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                collectionText.setTextColor(getResources().getColor(R.color.black));
                queryText.setTextColor(getResources().getColor(R.color.colorAccent));
                replaceFragment(new RoadConditionFragment());
                title.setText("公路路况");
                break;
        }
    }

}
