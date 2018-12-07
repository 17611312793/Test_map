package com.zlt.test_map.bean;

/***
 * 发送验证码
 */
public class GetCodeBean {

    /**
     * code : 200
     * msg : 发送成功
     * data : {"phone":"18233382571","yzm":"1234"}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * phone : 18233382571
         * yzm : 1234
         */

        private String phone;
        private String yzm;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getYzm() {
            return yzm;
        }

        public void setYzm(String yzm) {
            this.yzm = yzm;
        }
    }
}
