package com.zlt.test_map.http;


/**
 * 类描述：请求接口回调
 */
public interface RequestManagerImpl {

    /**
     * @param response 需要使用的数据的Json字符串
     */
    void onSuccess(String response, int requestType);

    /**
     * @param errorMsg
     * @param requestType
     */
    void onFail(NetBaseStatus errorMsg, int requestType);
}
