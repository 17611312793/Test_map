package com.zlt.test_map.ui.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zlt.test_map.R;
import com.zlt.test_map.base.BaseActivity;
import com.zlt.test_map.bean.GetCodeBean;
import com.zlt.test_map.mvp.contract.RegisterContract;
import com.zlt.test_map.mvp.presenter.RegisterPresenter;
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
注册页面
*/

public class RegisterActivity extends BaseActivity implements RegisterContract.View {

    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.ed_code)
    EditText edCode;
    @BindView(R.id.tv_getCode)
    TextView tvGetCode;
    @BindView(R.id.btu_register)
    Button btuRegister;
    private CountDownTimer timer;
    private RegisterPresenter presenter;
    private String yzm;

    @Override
    protected int setLayout() {
        //实现我们的PresenterImpl实现类
        presenter = new RegisterPresenter(this);
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_getCode, R.id.btu_register})
    public void onViewClicked(View view) {
        String phone = edPhone.getText().toString().trim();
        String code = edCode.getText().toString().trim();
        String check = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$";
        switch (view.getId()) {
            //获取验证码
            case R.id.tv_getCode:
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!phone.matches(check)) {
                    Toast.makeText(this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
                }
                Date date = new Date(System.currentTimeMillis());
                long time = date.getTime();
                String md5 = MD5Utils.getMD5(phone + time + "expressway");
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
                                    Toast.makeText(RegisterActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                                    Log.e("TAG", "成功");
                                    startCountDownTime();
                                    tvGetCode.setEnabled(false);
                                    tvGetCode.setTextColor(getResources().getColor(R.color.item_gray));
                                }
                            }
                        });
                break;
            //注册
            case R.id.btu_register:
                presenter.register();//当我们点击注册按钮的时候，调用一下PresenterImpl实现类中的register方法。
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

    /**
     * 这里做提示
     *
     * @param msg 我们所想提示的内容
     */
    @Override
    public void shouToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 在这做一个简单的注册成功后的跳转
     */
    @Override
    public void onSuccess() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * 对外提供获取用户输入的账号
     *
     * @return
     */
    @Override
    public String getName() {
        return edPhone.getText().toString();
    }

    /**
     * 对外提供获取用户输入的验证码
     *
     * @return
     */
    @Override
    public String getYZM() {
        return edCode.getText().toString();
    }

    @Override
    public String getIsYZM() {
        return yzm;
    }
}
