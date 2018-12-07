package com.zlt.test_map.mvp.contract;

public interface LoginContract {
    interface Model {
        /**
         * 接口回调
         *
         * @param
         * @param
         */
        void login(String name, String psw, Presenter presenter);


        interface OnDataChangeListener {
            /**
             * 请求完毕后，数据回显的方法
             *
             * @param s
             */
            void onDataChange(String s);
        }
    }

    interface View {

        void shouToast(String msg);

        void onSuccess();

        String getName();

        String getPassWord();

    }

    interface Presenter {

        void onSuccess();//登陆成功

        void onfail(String msg);//登陆失败

    }
}
