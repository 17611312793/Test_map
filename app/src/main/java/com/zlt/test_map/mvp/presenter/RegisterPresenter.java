package com.zlt.test_map.mvp.presenter;

import com.zlt.test_map.mvp.contract.RegisterContract;
import com.zlt.test_map.mvp.model.RegisterModel;

public class RegisterPresenter implements RegisterContract.Presenter {

    RegisterContract.View view;
    RegisterContract.Model model;//声明出我们Login2Model业务处理层的接口

    /**
     * 这里我们写一个带参构造方法
     *
     * @param //哪个View调用我们，就需要传哪个View的参数
     */
    public RegisterPresenter( RegisterContract.View view) {
        this.view = view;
        model = new RegisterModel();//再new出我们业务处理接口的具体实现类 }
    }

    /**
     * 在这定义一个注册的方法
     */
    public void register(){
        //将view中的参数获取出来。
        String name = view.getName();
        String yzm= view.getYZM();
        String isYZM = view.getIsYZM();
        /**
         * 调用一下业务处理model层的登陆方法
         * 参数一：账号
         * 参数二：验证码
         * 参数三：注册状态监听器
         */
        model.register(name,yzm,isYZM,this);
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
