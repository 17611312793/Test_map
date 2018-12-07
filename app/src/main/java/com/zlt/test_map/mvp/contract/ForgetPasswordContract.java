package com.zlt.test_map.mvp.contract;

public interface ForgetPasswordContract {
    interface Model {
        /**
         * 接口回调
         *
         * @param
         * @param
         */
        void forgetPassword(String name, String yzm, String psw,String isYzm, Presenter presenter);


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

        String getYZM();

        String getPsd();

        String getIsYZM();


    }

    interface Presenter {

        void onSuccess();//成功

        void onfail(String msg);//失败

    }
}
