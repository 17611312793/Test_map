package com.zlt.test_map.ui.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zlt.test_map.R;
import com.zlt.test_map.base.BaseActivity;
import com.zlt.test_map.bean.GetCodeBean;
import com.zlt.test_map.mvp.contract.ForgetPasswordContract;
import com.zlt.test_map.mvp.presenter.ForgetPasswordPresenter;
import com.zlt.test_map.utile.MD5Utils;
import com.zlt.test_map.utile.RetrofitUtils;

import java.text.DecimalFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/*
忘记密码页面
*/


public class ForgetPasswordActivity extends BaseActivity implements ForgetPasswordContract.View {

    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.ed_code)
    EditText edCode;
    @BindView(R.id.tv_getCode)
    TextView tvGetCode;
    @BindView(R.id.ed_password)
    EditText edPassword;
    @BindView(R.id.btu_register)
    Button btuRegister;
    private ForgetPasswordPresenter presenter;
    private CountDownTimer timer;
    private String yzm;

    @Override
    protected int setLayout() {
        presenter = new ForgetPasswordPresenter(this);
        return R.layout.activity_orget_password;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_getCode, R.id.btu_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_getCode:
                String phone = edPhone.getText().toString();
                String code = edCode.getText().toString();
                Date date = new Date(System.currentTimeMillis());
                long time = date.getTime();
                String md5 = MD5Utils.getMD5(phone + time + "expressway");
                Log.e("TAG",time+"");
                Log.e("TAG",md5);
                String check = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$";
                if (phone.isEmpty()) {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                } else if (!phone.matches(check)) {
                    Toast.makeText(this, "手机号错误", Toast.LENGTH_SHORT).show();
                } else {
                    RetrofitUtils.getInstance().getApiService().getCodePostData(phone, code, time + "", md5)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<GetCodeBean>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.e("Error",e.getMessage());
                                }

                                @Override
                                public void onNext(GetCodeBean bean) {
                                    int code = bean.getCode();
                                    if (code == 200) {
                                        yzm = bean.getData().getYzm();
                                        Toast.makeText(ForgetPasswordActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                                        Log.e("TAG", "成功");
                                        startCountDownTime();
                                        tvGetCode.setEnabled(false);
                                        tvGetCode.setTextColor(getResources().getColor(R.color.item_gray));
                                    }
                                }
                            });
                }
                break;
            case R.id.btu_register:
                presenter.forgetPassword();
                break;
        }
    }

    private void startCountDownTime() {
        timer = new CountDownTimer((long) 60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvGetCode.setText(String.format("%sS",
                        new DecimalFormat("0").format(millisUntilFinished / 1000)));
            }

            @Override
            public void onFinish() {
                timer.cancel();
                tvGetCode.setText("重新获取");
                tvGetCode.setEnabled(true);
                tvGetCode.setTextColor(getResources().getColor(R.color.colorAccent));
            }
        };
        timer.start();
    }

    @Override
    public void shouToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public String getName() {
        return edPhone.getText().toString();

    }

    @Override
    public String getYZM() {
        return edCode.getText().toString();

    }

    @Override
    public String getPsd() {
        return edPassword.getText().toString();

    }

    @Override
    public String getIsYZM() {
        return yzm;
    }
}
