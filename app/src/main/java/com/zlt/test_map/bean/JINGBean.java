package com.zlt.test_map.bean;

import com.google.gson.Gson;
import com.zlt.test_map.FixedPointBean;

import java.util.List;

/**
 * Created by Lenovo on 2018-11-24.
 */

public class JINGBean {


    /**
     * code : 200
     * msg : 增加成功!
     * data : ["1,1","2,2"]
     */

    private int code=200;
    private List<FixedPointBean> data;

    public static JINGBean objectFromData(String str) {

        return new Gson().fromJson(str, JINGBean.class);
    }

    public List<FixedPointBean> getData() {
        return data;
    }

    public void setData(List<FixedPointBean> data) {
        this.data = data;
    }
}
