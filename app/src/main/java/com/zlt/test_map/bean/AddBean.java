package com.zlt.test_map.bean;

import com.google.gson.Gson;

/**
 * Created by Lenovo on 2018-11-24.
 */

public class AddBean {

    /**
     * code : 200
     * msg : 增加成功!
     * data : {"id":"2"}
     */

    private int code;
    private String msg;
    private DataBean data;

    public static AddBean objectFromData(String str) {

        return new Gson().fromJson(str, AddBean.class);
    }

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
         * id : 2
         */

        private String id;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
