package com.zlt.test_map.bean;

/***
 * 登陆
 */
public class LoginBean {

    /**
     * code : 200
     * msg : ok
     * data : {"status":1,"user_id":4,"user_token":"adcb50c6b5cac4bd12bb9a31b4279b66"}
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
         * status : 1
         * user_id : 4
         * user_token : adcb50c6b5cac4bd12bb9a31b4279b66
         */

        private int status;
        private int user_id;
        private String user_token;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUser_token() {
            return user_token;
        }

        public void setUser_token(String user_token) {
            this.user_token = user_token;
        }
    }
}
