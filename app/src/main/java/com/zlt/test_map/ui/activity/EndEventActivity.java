package com.zlt.test_map.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.zlt.test_map.R;
import com.zlt.test_map.base.BaseActivity;
import com.zlt.test_map.bean.CompletedBean;
import com.zlt.test_map.ui.map.ToastUtil;
import com.zlt.test_map.utile.RetrofitUtils;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 结束事件
 */
public class EndEventActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.ed_type)
    EditText edType;
    @BindView(R.id.ed_address)
    EditText edAddress;
    @BindView(R.id.ed_start_time)
    EditText edStartTime;
    @BindView(R.id.ed_end_time)
    EditText edEndTime;
    @BindView(R.id.et_list_of_participants)
    EditText etListOfParticipants;
    @BindView(R.id.et_leadership_decisions)
    EditText etLeadershipDecisions;
    @BindView(R.id.et_take_steps)
    EditText etTakeSteps;
    @BindView(R.id.et_process_details)
    EditText etProcessDetails;
    @BindView(R.id.et_time_summary)
    EditText etTimeSummary;
    @BindView(R.id.ed_vehicle)
    EditText edVehicle;
    @BindView(R.id.ed_material)
    EditText edMaterial;

    @Override
    protected int setLayout() {
        return R.layout.activity_end_event;
    }

    @Override
    protected void initView() {
        title.setText("结束事件");
    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.go_back)
    public void onGoBack() {
        finish();
    }

    @OnClick(R.id.bt_commit)
    public void onCommit() {
        RetrofitUtils.getInstance().getApiService().getCompletedPostData("", "", 1, 1,
                edStartTime.getText().toString(),
                edEndTime.getText().toString(),
                etListOfParticipants.getText().toString(),
                etLeadershipDecisions.getText().toString(),
                etTakeSteps.getText().toString(),
                edVehicle.getText().toString(),
                edMaterial.getText().toString(),
                etProcessDetails.getText().toString(),
                etTimeSummary.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CompletedBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", e.getMessage());
                    }

                    @Override
                    public void onNext(CompletedBean completedBean) {
                        int code = completedBean.getCode();
                        if (code == 200) {
                            startActivity(new Intent(EndEventActivity.this, HistoryEventDetailsActivity.class));
                        } else {
                            ToastUtil.show(EndEventActivity.this, completedBean.getMsg());
                        }
                    }
                });
    }

    @OnClick(R.id.bt_commit)
    public void onViewClicked() {
    }
}
