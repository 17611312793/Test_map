package com.zlt.test_map.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zlt.test_map.R;
import com.zlt.test_map.base.BaseActivity;
import com.zlt.test_map.bean.AcceptanceBean;
import com.zlt.test_map.utile.RetrofitUtils;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/***
 * 待验收任务————是否通过
 */

public class PendingAcceptancTesk03Activity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.images1)
    ImageView images1;
    @BindView(R.id.images2)
    ImageView images2;
    @BindView(R.id.backup)
    TextView edbackup;
    @BindView(R.id.tv_routecode)
    TextView tvRoutecode;
    @BindView(R.id.tv_routeclaim)
    TextView tvRouteclaim;
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_ingnum)
    TextView tvIngnum;
    @BindView(R.id.tv_area)
    TextView tvArea;
    private int id;
    private Intent intent;

    @Override
    protected int setLayout() {
        return R.layout.activity_pending_acceptanc_tesk03;
    }

    @Override
    protected void initView() {
        title.setText("待验收任务");
        intent = getIntent();
        id = intent.getIntExtra("id",0);
        String area = intent.getStringExtra("area");
        String ingnum = intent.getStringExtra("ingnum");
        String position1 = intent.getStringExtra("position1");
        String routeclaim = intent.getStringExtra("routeclaim");
        String routecode = intent.getStringExtra("routecode");
        String type = intent.getStringExtra("type");
        String images = intent.getStringExtra("images");
//        String backup = intent.getStringExtra("backup");
        tvArea.setText(area);
        tvIngnum.setText(ingnum);
        tvPosition.setText(position1);
        tvRouteclaim.setText(routeclaim);
        tvRoutecode.setText(routecode);
        tvType.setText(type);
//        edbackup.setText(backup);

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.go_back, R.id.bt_no, R.id.bt_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.go_back:
                finish();
                break;
            case R.id.bt_no:
                RetrofitUtils.getInstance().getApiService().getAcceptancePostData(id, 2)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<AcceptanceBean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("Error", e.getMessage());
                            }

                            @Override
                            public void onNext(AcceptanceBean acceptanceBean) {
                                int code = acceptanceBean.getCode();
                                if (code == 200) {
                                    Toast.makeText(PendingAcceptancTesk03Activity.this, "不通过", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        });
                break;
            case R.id.bt_ok:
                RetrofitUtils.getInstance().getApiService().getAcceptancePostData(id, 1)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<AcceptanceBean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("Error", e.getMessage());
                            }

                            @Override
                            public void onNext(AcceptanceBean acceptanceBean) {
                                int code = acceptanceBean.getCode();
                                if (code == 200) {
                                    Toast.makeText(PendingAcceptancTesk03Activity.this, "通过", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        });
                break;
        }
    }
}
