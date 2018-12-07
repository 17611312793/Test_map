package com.zlt.test_map.mvp.model;

import android.util.Log;

import com.zlt.test_map.bean.LoginBean;
import com.zlt.test_map.mvp.contract.LoginContract;
import com.zlt.test_map.utile.RetrofitUtils;

import org.greenrobot.eventbus.EventBus;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginModel implements LoginContract.Model {

    @Override
    public void login(String name, String psw, LoginContract.Presenter presenter) {

        /**
         * 这里我只是对数据做一个简单的判断，大神勿喷。
         */
//        String check = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$";
//        if (name.isEmpty()) {
//            presenter.onfail("请输入手机号");
//        } else if (!name.matches(check)) {
//            presenter.onfail("手机号错误");
//        } else if (psw.isEmpty()) {
//            presenter.onfail("请输入密码");
//        } else if (name.matches(check)) {
            RetrofitUtils.getInstance().getApiService()
                    .getLoginPostData("", "", "", 1, name, psw)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<LoginBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("Error", e.getMessage());
                        }

                        @Override
                        public void onNext(LoginBean bean) {
                            int code = bean.getCode();
                            switch (code) {
                                case 200:
                                    Log.e("TAG", bean.getMsg());
                                    presenter.onfail("登陆成功");
                                    presenter.onSuccess();
                                    String user_token = bean.getData().getUser_token();
                                    EventBus.getDefault().postSticky(user_token);//发送粘性事件

                                    break;
                                case 201:
                                    Log.e("TAG", bean.getMsg());
                                    presenter.onfail("账号或密码错误！");
                                    break;
                            }
                        }
                    });
        }
    }
//}
