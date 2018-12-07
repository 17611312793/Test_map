package com.zlt.test_map.ui.activity;

import android.util.Log;
import android.widget.TextView;

import com.zlt.test_map.R;
import com.zlt.test_map.base.BaseActivity;
import com.zlt.test_map.bean.HistoryBean;
import com.zlt.test_map.utile.RetrofitUtils;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/***
 * 历史事件详情
 */
public class HistoryEventDetailsActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_event_type)
    TextView tvEventType;
    @BindView(R.id.tv_event)
    TextView tvEvent;
    @BindView(R.id.tv_starttime)
    TextView tvStarttime;
    @BindView(R.id.tv_endtime)
    TextView tvEndtime;
    @BindView(R.id.tv_participant)
    TextView tvParticipant;
    @BindView(R.id.tv_leader)
    TextView tvLeader;
    @BindView(R.id.tv_takesteps)
    TextView tvTakesteps;
    @BindView(R.id.tv_car)
    TextView tvCar;
    @BindView(R.id.tv_material)
    TextView tvMaterial;
    @BindView(R.id.tv_processdetail)
    TextView tvProcessdetail;
    @BindView(R.id.tv_timesummary)
    TextView tvTimesummary;

    @Override
    protected int setLayout() {
        return R.layout.activity_history_event_details;
    }

    @Override
    protected void initView() {
        title.setText("历史事件详情");
    }

    @Override
    protected void initData() {
        RetrofitUtils.getInstance().getApiService().getHistoryGetData(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HistoryBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", e.getMessage());
                    }

                    @Override
                    public void onNext(HistoryBean historyBean) {
                        int code = historyBean.getCode();
                        if (code == 200) {
                            HistoryBean.DataBean.ListBean list = historyBean.getData().getList();
                            tvCar.setText(list.getCar());
                            tvEndtime.setText(list.getEndtime());
                            tvEvent.setText(list.getEvent());
                            tvEventType.setText(list.getEvent_type());
                            tvLeader.setText(list.getLeader());
                            tvMaterial.setText(list.getMaterial());
                            tvParticipant.setText(list.getParticipant());
                            tvProcessdetail.setText(list.getProcessdetail());
                            tvStarttime.setText(list.getStarttime());
                            tvTakesteps.setText(list.getTakesteps());
                            tvTimesummary.setText(list.getTimesummary());
                        }
                    }
                });
    }

    @OnClick(R.id.go_back)
    public void onGoBack() {
        finish();
    }

}
