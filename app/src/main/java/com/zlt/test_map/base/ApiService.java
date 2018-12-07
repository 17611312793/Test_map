package com.zlt.test_map.base;

import com.zlt.test_map.bean.AcceptanceBean;
import com.zlt.test_map.bean.AddBean;
import com.zlt.test_map.bean.AddDetailBean;
import com.zlt.test_map.bean.ChooseTownBean;
import com.zlt.test_map.bean.CollectionBean;
import com.zlt.test_map.bean.CompletedBean;
import com.zlt.test_map.bean.ConditionQueryBean;
import com.zlt.test_map.bean.ConfirmBean;
import com.zlt.test_map.bean.CuringShowBean;
import com.zlt.test_map.bean.ForgetPasswordBean;
import com.zlt.test_map.bean.GetCodeBean;
import com.zlt.test_map.bean.HistoryBean;
import com.zlt.test_map.bean.LaunchBean;
import com.zlt.test_map.bean.LoginBean;
import com.zlt.test_map.bean.NewsBean;
import com.zlt.test_map.bean.PendingaccBean;
import com.zlt.test_map.bean.QueryShowBean;
import com.zlt.test_map.bean.ReceiptBean;
import com.zlt.test_map.bean.RegisterBean;
import com.zlt.test_map.bean.RepairBean;
import com.zlt.test_map.bean.RoadConditionAddBean;
import com.zlt.test_map.bean.RoadConditionBean;
import com.zlt.test_map.bean.StartAcceptanceBean;
import com.zlt.test_map.bean.SupplierBean;
import com.zlt.test_map.bean.SurveyBean;
import com.zlt.test_map.bean.TobereceivedBean;
import com.zlt.test_map.bean.WayTypeBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {

    //注册
    @FormUrlEncoded
    @POST("index.php/api/login/do_login")
    Observable<RegisterBean> getRegisterPostData(@Field("login_addr") String login_addr,
                                                 @Field("phone_info") String phone_info,
                                                 @Field("app_version") String app_version,
                                                 @Field("login_type") int login_type,
                                                 @Field("phone") String phone,
                                                 @Field("yzm") String yzm,
                                                 @Field("password") String password);

    //登陆
    @FormUrlEncoded
    @POST("index.php/api/login/do_login")
    Observable<LoginBean> getLoginPostData(@Field("login_addr") String login_addr,
                                           @Field("phone_info") String phone_info,
                                           @Field("app_version") String app_version,
                                           @Field("login_type") int login_type,
                                           @Field("phone") String phone,
                                           @Field("password") String password);

    //忘记密码
    @FormUrlEncoded
    @POST("index.php/api/login/do_login")
    Observable<ForgetPasswordBean> getForgetPasswordPostData(@Field("login_addr") String login_addr,
                                                             @Field("phone_info") String phone_info,
                                                             @Field("app_version") String app_version,
                                                             @Field("login_type") int login_type,
                                                             @Field("phone") String phone,
                                                             @Field("yzm") String yzm,
                                                             @Field("password") String password);


    //发送验证码
    @FormUrlEncoded
    @POST("index.php/api/sendmsg/do_sendmsg")
    Observable<GetCodeBean> getCodePostData(@Field("phone") String phone,
                                            @Field("yzm") String yzm,
                                            @Field("datetime") String datetime,
                                            @Field("sign") String sign);

    //选择乡镇
    @GET("api/gettown/town")
    Observable<ChooseTownBean> getChooseTownGetData();

    //选择道路类型
    @GET("api/gettown/getwaytype")
    Observable<WayTypeBean> getWayTypeGetData();

    //公路状况列表
    @GET("api/roadcondition/index")
    Observable<RoadConditionBean> getRoadConditionGetData();

    //路况采集展示接口
    @GET("api/roadcondition/collection")
    Observable<CollectionBean> getCollectionGetData(@Query("id") int id);

    //发起路况采集接口
    @FormUrlEncoded
    @POST("api/roadcondition/add")
    Observable<RoadConditionAddBean> getRoadConditionAddPostData(@Field("roadmap_id") int roadmap_id,
                                                                 @Field("routecode") String routecode,
                                                                 @Field("routeclaim") String routeclaim,
                                                                 @Field("ingnum") String ingnum,
                                                                 @Field("position") String position,
                                                                 @Field("type") String type,
                                                                 @Field("area") String area,
                                                                 @Field("images") String images);

    //路况查询列表接口
    @GET("api/roadcondition/conditionquery")
    Observable<ConditionQueryBean> getConditionQueryGetData();

    //路况查询详情
    @GET("api/roadcondition/queryshow")
    Observable<QueryShowBean> getQueryShowGetData(@Query("id") int id);

    //养护管理列表展示接口
    @GET("api/curing/curingshow")
    Observable<CuringShowBean> getCuringShowGetData();

    //供应商接口
    @GET("api/curing/supplier")
    Observable<SupplierBean> getSupplier();

    //确认下单
    @FormUrlEncoded
    @POST("api/curing/confirm")
    Observable<ConfirmBean> getConfirmPostData(@Field("condition_id") int condition_id,
                                               @Field("supplier_id") int supplier_id);

    //接单列表展示接口
    @GET("api/curingb/tobereceived")
    Observable<TobereceivedBean> getTobereceivedGetData(@Query("user_token") String user_token);

    //确认接单接口
    @FormUrlEncoded
    @POST("api/curingb/receipt")
    Observable<ReceiptBean> getReceiptPostData(@Field("user_token") String user_token,
                                               @Field("condition_id") int condition_id);

    //维修信息（提交验收）
    @FormUrlEncoded
    @POST("api/curingb/repair")
    Observable<RepairBean> getRepairPostData(@Field("user_token") String user_token,
                                             @Field("condition_id") String condition_id,
                                             @Field("images") String images,
                                             @Field("backup") String backup);

    //待验收列表接口
    @GET("api/curingb/pendingacc")
    Observable<PendingaccBean> getPendingaccGetData(@Query("user_token") String user_token);

    //开始验收列表展示接口
    @GET("api/curingb/startacceptance")
    Observable<StartAcceptanceBean> getStartAcceptanceGetData(@Query("id") String id);

    //验收是否通过接口
    @FormUrlEncoded
    @POST("api/curingb/acceptance")
    Observable<AcceptanceBean> getAcceptancePostData(@Field("id") int id,
                                                     @Field("whether") int whether);

    //新事件列表
    @GET("api/emergency/news")
    Observable<NewsBean> getNewsGetData();

    //获取事件概况接口
    @GET("api/emergency/survey")
    Observable<SurveyBean> getSurveyGetData(@Query("id") String id);

    //处置完成请求接口
    @FormUrlEncoded
    @POST("api/emergency/completed")
    Observable<CompletedBean> getCompletedPostData(@Field("event") String event,
                                                   @Field("site") String site,
                                                   @Field("x") int x,
                                                   @Field("y") int y,
                                                   @Field("starttime") String starttime,
                                                   @Field("endtime") String endtime,
                                                   @Field("participant") String participant,
                                                   @Field("leader") String leader,
                                                   @Field("takesteps") String takesteps,
                                                   @Field("car") String car,
                                                   @Field("material") String material,
                                                   @Field("processdetail") String processdetail,
                                                   @Field("timesummary") String timesummary);

    //发起新事件接口
    @FormUrlEncoded
    @POST("api/emergency/launch")
    Observable<LaunchBean> getLaunchPostData(@Field("title") String title,
                                             @Field("site") String site,
                                             @Field("x") String x,
                                             @Field("y") String y,
                                             @Field("conditions") String conditions,
                                             @Field("sceneimg") String sceneimg);

    //历史事件接口
    @GET("api/emergency/history")
    Observable<HistoryBean> getHistoryGetData(@Query("id") int id);

    // 新增路线(按钮释义: 开始采集)
    @FormUrlEncoded
    @POST("api/map/add")
    Observable<AddBean> getAddPostData(@Field("routecode") String routecode,
                                          @Field("routeclaim") String routeclaim,
                                          @Field("startingnum") String startingnum,
                                          @Field("routetype") String routetype,
                                          @Field("routewidth") String routewidth,
                                          @Field("subgradewidth") String subgradewidth);


    // 新增路线(按钮释义: 停止采集)
    @FormUrlEncoded
    @POST("api/map/add_detail")
    Observable<AddDetailBean> getLaunchPostData(@Field("road_id") int road_id,
                                                @Field("data") String data);
}
