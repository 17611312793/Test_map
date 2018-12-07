package com.zlt.test_map.http;

import com.google.gson.Gson;

/**
 * Created by Mars on 2018/3/9 16:17.
 * 描述：网络状态
 */

public class NetBaseStatus {
    /**
     * code : 200
     * msg : 增加成功!
     */

    private int code;
    private String msg;

    public NetBaseStatus(String msg) {
        this.msg = msg;
    }

    public static NetBaseStatus objectFromData(String str) {

        return new Gson().fromJson(str, NetBaseStatus.class);
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
}
