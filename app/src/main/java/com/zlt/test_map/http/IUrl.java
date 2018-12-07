package com.zlt.test_map.http;


/**
 * Created by Lenovo on 2018-10-8.
 * 接口
 */

public interface IUrl {
    /**
     *
     */
    String Url_LogIn = Constants.HOST1 + "api/map/add";
    /**
     *
     */
    String Url_Add_Detail = Constants.HOST1 + "/api/map/add_detail";
    String Url_Getdata = Constants.HOST1 + "/api/map/getdata";
    String Url_CheckUpdate= "http://yitian.331cn.cn/api.php/user/checkUpdate";

    String Url_GetCode= Constants.HOST1 + "/index.php/api/sendmsg/do_sendmsg";

}
