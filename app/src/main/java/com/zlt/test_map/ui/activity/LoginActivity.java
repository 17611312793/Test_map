package com.zlt.test_map.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zlt.test_map.R;
import com.zlt.test_map.base.BaseActivity;
import com.zlt.test_map.mvp.contract.LoginContract;
import com.zlt.test_map.mvp.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/*
登陆页面
*/

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_password)
    EditText edPassword;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.btu_login)
    Button btuLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    private LoginPresenter presenter;

    @Override
    protected int setLayout() {
        //实现我们的PresenterImpl实现类
        presenter = new LoginPresenter(this);
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_forget_password, R.id.btu_login, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_forget_password:
                startActivity(new Intent(this, ForgetPasswordActivity.class));
                break;
            case R.id.btu_login:
                presenter.login();
                break;
            case R.id.tv_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
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
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    /**
     * 对外提供获取用户输入的账号
     *
     * @return
     */
    @Override
    public String getName() {
        return edName.getText().toString();
    }

    /**
     * 对外提供获取用户输入的密码
     *
     * @return
     */
    @Override
    public String getPassWord() {
        return edPassword.getText().toString();
    }
}
