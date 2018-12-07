package com.zlt.test_map.ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zlt.test_map.R;
import com.zlt.test_map.base.BaseActivity;
import com.zlt.test_map.bean.LaunchBean;
import com.zlt.test_map.utile.RetrofitUtils;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/***
 * 新事件页面
 */

public class NewEventActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.site)
    TextView site;
    @BindView(R.id.ed_conditions)
    EditText edConditions;

    @Override
    protected int setLayout() {
        return R.layout.activity_new_event;
    }

    @Override
    protected void initView() {
        title.setText("新事件");

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.go_back, R.id.event_type, R.id.address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.go_back:
                finish();
                break;
            case R.id.event_type:
                break;
            case R.id.address:
                break;
        }
    }

    @OnClick(R.id.bt_release)
    public void onRelease() {
        RetrofitUtils.getInstance().getApiService().getLaunchPostData(tvTitle.getText().toString(), site.getText().toString(), "123", "11", edConditions.getText().toString(), "tu.png")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LaunchBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", e.getMessage());
                    }

                    @Override
                    public void onNext(LaunchBean launchBean) {
                        int code = launchBean.getCode();
                        if (code == 200) {
                            finish();
                        }else {
                            Toast.makeText(NewEventActivity.this,launchBean.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
