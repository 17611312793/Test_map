package com.zlt.test_map.http;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * Created by Lenovo on 2018-10-8.
 */

public class HttpHelp implements IUrl {

    private Context mContext;

    public HttpHelp(Context context) {
        mContext = context;
    }

    /**
     * @param requestType
     * @param routecode      路线编码
     * @param routeclaim     路线名称
     * @param startingnum    起点桩号
     * @param routetype      路面类型
     * @param routewidth     路面宽度
     * @param subgradewidth  路基宽度
     * @param requestManager
     */

    //登录
    public void LogIn(final int requestType, String routecode, String routeclaim,
                      String startingnum, String routetype, String routewidth,
                      String subgradewidth, final RequestManagerImpl requestManager) {

        HttpParams mapData = new HttpParams();
        mapData.put("routecode", routecode);
        mapData.put("routeclaim", routeclaim);
        mapData.put("startingnum", startingnum);
        mapData.put("routetype", routetype);
        mapData.put("routewidth", routewidth);
        mapData.put("subgradewidth", subgradewidth);
        OkGo.<String>post(IUrl.Url_LogIn)
                .tag(this)
                .params(mapData)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("XXX", "--------------------");
                        analysis(requestType, requestManager, response);
                    }
                });

    }

    //添加细节
    public void Add_Detail(final int requestType, String id,
                           String data, final RequestManagerImpl requestManager) {

        HttpParams mapData = new HttpParams();
        mapData.put("road_id", id);
        mapData.put("data", data);
        OkGo.<String>post(IUrl.Url_Add_Detail)
                .tag(this)
                .params(mapData)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("XXX", "--------------------");
                        analysis(requestType, requestManager, response);
                    }
                });

    }

    //获取数据
    public void Getdata(final int requestType, final RequestManagerImpl requestManager) {
        HttpParams mapData = new HttpParams();
        OkGo.<String>post(IUrl.Url_Getdata)
                .tag(this)
                .params(mapData)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("XXX", "--------------------");
                        analysis(requestType, requestManager, response);
                    }
                });

    }

    //检测更新
    public void CheckUpdate(final int requestType, String app_version, final RequestManagerImpl requestManager) {
        HttpParams mapData = new HttpParams();
        mapData.put("app_version", app_version);
        OkGo.<String>post(IUrl.Url_CheckUpdate)
                .tag(this)
                .params(mapData)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        analysis(requestType, requestManager, response);
                    }
                });

    }

    //分析
    private void analysis(final int requestType, final RequestManagerImpl requestManager, Response<String> response) {
        try {
            if (TextUtils.isEmpty(response.body())) {
                requestManager.onFail(new NetBaseStatus("数据异常"), requestType);
            } else {
                NetBaseStatus netBaseStatus = new Gson().fromJson(response.body().toString(), NetBaseStatus.class);
                if (netBaseStatus.getCode() == 200) {
                    requestManager.onSuccess(response.body().toString(), requestType);
                } else {
                    requestManager.onFail(netBaseStatus, requestType);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            requestManager.onFail(new NetBaseStatus("数据异常"), requestType);
        }
    }

    //发送验证码
    public void GetCode(final int requestType,  String phone, String yzm, String datetime, String sign, final RequestManagerImpl requestManager) {

        HttpParams mapData = new HttpParams();
        mapData.put("phone", phone);
        mapData.put("yzm", yzm);
        mapData.put("datetime", datetime);
        mapData.put("sign", sign);
        OkGo.<String>post(IUrl.Url_GetCode)
                .tag(this)
                .params(mapData)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("XXX", "--------------------");
                        analysis(requestType, requestManager, response);
                    }
                });

    }


}
