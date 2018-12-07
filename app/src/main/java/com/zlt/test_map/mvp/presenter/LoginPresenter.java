package com.zlt.test_map.mvp.presenter;

import com.zlt.test_map.mvp.contract.LoginContract;
import com.zlt.test_map.mvp.model.LoginModel;

public class LoginPresenter implements LoginContract.Presenter {

    LoginContract.View view;
    LoginContract.Model model;//声明出我们Login2Model业务处理层的接口


    /**
     * 这里我们写一个带参构造方法
     *
     * @param //哪个View调用我们，就需要传哪个View的参数
     */
    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        model = new LoginModel();//再new出我们业务处理接口的具体实现类 }
    }


    /**
     * 在这定义一个登陆的方法
     */
    public void login(){
        //将view中的参数获取出来。
        String name = view.getName();
        String psw = view.getPassWord();
        /**
         * 调用一下业务处理model层的登陆方法
         * 参数一：账号
         * 参数二：密码
         * 参数三：登陆状态监听器
         */
        model.login(name,psw,this);
    }


    /**
     * 下面都是实现我们刚刚在接口中的方法
     */
    @Override
    public void onSuccess() {
        view.onSuccess();
    }

    @Override
    public void onfail(String msg) {
        view.shouToast(msg);
    }
}
