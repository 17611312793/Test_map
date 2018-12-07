package com.zlt.test_map.mvp.model;

import android.util.Log;

import com.zlt.test_map.bean.RegisterBean;
import com.zlt.test_map.mvp.contract.ForgetPasswordContract;
import com.zlt.test_map.utile.RetrofitUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ForgetPasswordModel implements ForgetPasswordContract.Model {
    @Override
    public void forgetPassword(String name, String yzm, String psw, String isYzm, ForgetPasswordContract.Presenter presenter) {
        /**
         * 这里我只是对数据做一个简单的判断
         */
        String check = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$";
        if (name.isEmpty()) {
            presenter.onfail("请输入手机号");
        } else if (!name.matches(check)) {
            presenter.onfail("手机号错误");
        } else if (yzm.isEmpty()) {
            presenter.onfail("请输入验证码");
        } else if (!yzm.equals(isYzm)) {
            presenter.onfail("验证码错误");
        } else if (psw.isEmpty()&&yzm.equals(isYzm)) {
            presenter.onfail("请输入密码");
        } else if (name.matches(check)) {
            RetrofitUtils.getInstance().getApiService()
                    .getRegisterPostData("", "", "", 2, name, yzm, psw)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<RegisterBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("Error", e.getMessage());
                        }

                        @Override
                        public void onNext(RegisterBean bean) {
                            int code = bean.getCode();
                            String msg = bean.getMsg();
                            switch (code) {
                                case 200:
                                    if (msg.equals("ok")) {
                                        presenter.onfail("验证码发送成功");
                                    } else if (msg.equals("密码修改成功！")) {
                                        presenter.onfail("密码修改成功");
                                        presenter.onSuccess();
                                    }
                                    break;
                            }
                        }
                    });
        }
    }
}
