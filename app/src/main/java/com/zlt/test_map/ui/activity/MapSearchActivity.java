package com.zlt.test_map.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.zlt.test_map.R;
import com.zlt.test_map.ui.map.DrivingRouteOverlay;
import com.zlt.test_map.ui.map.ToastUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//import com.amap.api.maps2d.model.LatLng;
//import com.amap.api.maps2d.model.MarkerOptions;

/***
 * 地图搜索页面
 */

public class MapSearchActivity extends AppCompatActivity implements RouteSearch.OnRouteSearchListener, AMapLocationListener, GeocodeSearch.OnGeocodeSearchListener {
    MapView mMapView = null;
    AMap aMap;
    @BindView(R.id.et_search)
    EditText etSearch;
    MyLocationStyle myLocationStyle;
    String[] permission = {Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE};
    // 定义 Marker 点击事件监听
    AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener() {
        // marker 对象被点击时回调的接口
        // 返回 true 则表示接口已响应事件，否则返回false
        @Override
        public boolean onMarkerClick(Marker marker) {
            Toast.makeText(MapSearchActivity.this, marker.getTitle() + "---" + marker.getSnippet(), Toast.LENGTH_SHORT).show();
            return true;
        }
    };
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //AMapLocationClientOption对象用来设置发起定位的模式和相关参数。
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private String city;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_search);
        ButterKnife.bind(this);
//        //获取地图控件引用
//        mMapView = (MapView) findViewById(R.id.map);
//        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
//        mMapView.onCreate(savedInstanceState);
//        //初始化地图控制器对象
//        if (aMap == null) {
//            aMap = mMapView.getMap();
//        }

        //这里以ACCESS_COARSE_LOCATION为例
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, permission,
                    100);//自定义的code
        }
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        // 连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        // myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);//开始定位
        //设置是否显示定位小蓝点，用于满足只想使用定位，不想使用定位小蓝点的场景，设置false以后图面上不再有定位蓝点的概念，但是会持续回调位置信息。
        myLocationStyle.showMyLocation(true);
        //监听地图上的位置信息
        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                Log.e("TAG", location.getLatitude() + "---" + location.getLongitude());
            }
        });

        String[] arr = new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE};

//这里以ACCESS_COARSE_LOCATION为例
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, arr,
                    100);//自定义的code
        }


        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(false);
        mLocationOption.setInterval(8000);

        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
        initView();
    }

    private void initView() {
        // 绑定 Marker 被点击事件
        aMap.setOnMarkerClickListener(markerClickListener);
        etSearch.setText("昌平");
//        //初始化 RouteSearch 对象
//        RouteSearch routeSearch = new RouteSearch(this);
//        //设置数据回调监听器
//        routeSearch.setRouteSearchListener(this);
//        /***
//         * 通过 WalkRouteQuery(RouteSearch.FromAndTo fromAndTo, int mode) 设置搜索条件。其中：
//         *
//         * fromAndTo，路径的起终点；
//         * mode，计算路径的模式。SDK提供两种模式：RouteSearch.WALK_DEFAULT 和 RouteSearch.WALK_MULTI_PATH。
//         */
//        //初始化query对象，fromAndTo是包含起终点信息，walkMode是步行路径规划的模式
//        RouteSearch.WalkRouteQuery query = new RouteSearch.WalkRouteQuery(fromAndTo, walkMode);
//        routeSearch.calculateWalkRouteAsyn(query);//开始算路


        // TODO POI检索

//        etSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO POI检索   兴趣点检索
//                //keyWord表示搜索字符串，
//                //第二个参数表示POI搜索类型，二者选填其一，选用POI搜索类型时建议填写类型代码，码表可以参考下方（而非文字）
//                //cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
//                PoiSearch.Query query = new PoiSearch.Query(etSearch.getText().toString(), "", "昌平");
//                query.setPageSize(50);// 设置每页最多返回多少条poiitem
//                query.setPageNum(1);//设置查询页码
//                PoiSearch poiSearch = new PoiSearch(MapSearchActivity.this, query);
//                poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
//                    @Override
//                    public void onPoiSearched(PoiResult poiResult, int i) {
//                        //搜索结果返回
//                        ArrayList<PoiItem> pois = poiResult.getPois();
//                        aMap.clear();//清除之前的标记点
//                        for (int j = 0; j < pois.size(); j++) {
//                            PoiItem poiItem = pois.get(j);
//                            Log.e("TAG", poiItem.getTitle() + "---" + poiItem.getSnippet());
//                            LatLonPoint latLonPoint = poiItem.getLatLonPoint();//获取具体兴趣点的经纬度
//                            LatLng latLng = new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
//                            final Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title(poiItem.getTitle()).snippet(poiItem.getSnippet()));
//                        }
//                    }
//
//                    @Override
//                    public void onPoiItemSearched(PoiItem poiItem, int i) {
//
//                    }
//                });
//                poiSearch.searchPOIAsyn();//开启搜索
//
//
//            }
//        });


        //获取起点坐标
        etSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PoiSearch.Query query = new PoiSearch.Query(etSearch.getText().toString(), "", "大兴");
                //keyWord表示搜索字符串，
                //第二个参数表示POI搜索类型，二者选填其一，选用POI搜索类型时建议填写类型代码，码表可以参考下方（而非文字）
                //cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
                query.setPageSize(50);// 设置每页最多返回多少条poiitem
                query.setPageNum(1);//设置查询页码
                PoiSearch poiSearch = new PoiSearch(MapSearchActivity.this, query);
                poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
                    @Override
                    public void onPoiSearched(PoiResult poiResult, int i) {
                        ArrayList<PoiItem> pois = poiResult.getPois();
                        aMap.clear();
                        for (int j = 0; j < pois.size(); j++) {
                            PoiItem poiItem = pois.get(j);
                            Log.e("TAG", poiItem.getLatLonPoint().toString() + "--" + poiItem.getTitle() + "--" + poiItem.getSnippet());
                            LatLonPoint latLonPoint = poiItem.getLatLonPoint();
                            LatLng latLng = new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
                            final Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title(poiItem.getTitle()).snippet(poiItem.getSnippet()));
                            GeocodeSearch geocodeSearch = new GeocodeSearch(MapSearchActivity.this);

                            geocodeSearch.setOnGeocodeSearchListener(MapSearchActivity.this);
                            GeocodeQuery query = new GeocodeQuery(etSearch.getText().toString(), "010");

                            geocodeSearch.getFromLocationNameAsyn(query);
                        }
                    }

                    @Override
                    public void onPoiItemSearched(PoiItem poiItem, int i) {

                    }
                });
                poiSearch.searchPOIAsyn();
            }
        });

        initSelfLocation();
    }

    private void initSelfLocation() {

        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(60000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
//aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        myLocationStyle.showMyLocation(true);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //可在此继续其他操作。
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i])
                    != PackageManager.PERMISSION_GRANTED) {
                finish();
            }
        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length() - 1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @OnClick({R.id.GoBack, R.id.close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.GoBack:
                finish();
                break;
            case R.id.close:
                etSearch.setText("");
                break;
        }
    }

    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {//公交车

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult result, int errorCode) {//驾驶
        aMap.clear();// 清理地图上的所有覆盖物
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getPaths() != null) {
                if (result.getPaths().size() > 0) {
                    final DrivePath drivePath = result.getPaths()
                            .get(0);
                    DrivingRouteOverlay drivingRouteOverlay = new DrivingRouteOverlay(
                            MapSearchActivity.this, aMap, drivePath,
                            result.getStartPos(),
                            result.getTargetPos(), null);
                    drivingRouteOverlay.setNodeIconVisibility(false);//设置节点marker是否显示
                    drivingRouteOverlay.setIsColorfulline(true);//是否用颜色展示交通拥堵情况，默认true
                    drivingRouteOverlay.removeFromMap();
                    drivingRouteOverlay.addToMap();
                    drivingRouteOverlay.zoomToSpan();


                } else if (result != null && result.getPaths() == null) {
                    ToastUtil.show(MapSearchActivity.this, "对不起，没有搜索到相关数据!");
                }

            } else {
                ToastUtil.show(MapSearchActivity.this, "对不起，没有搜索到相关数据!");
            }
        } else {
            ToastUtil.showerror(this.getApplicationContext(), errorCode);
        }
    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {//步行

    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {//自行车

    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {

    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
        Log.e("TAG222222222", geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint().toString());
        LatLonPoint latLonPoint = geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint();
        LatLng latLng = new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
        final Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title("").snippet(""));

        Toast.makeText(MapSearchActivity.this, marker.getPosition().toString() + "--" + marker.getTitle() + "--" + marker.getSnippet(), Toast.LENGTH_SHORT).show();
        //获取起点坐标
        Location myLocation = aMap.getMyLocation();
        LatLonPoint start = new LatLonPoint(myLocation.getLatitude(), myLocation.getLongitude());
        //获取目的地坐标

        LatLonPoint end = new LatLonPoint(latLonPoint.getLatitude(), latLonPoint.getLongitude());

        //发起路径规划的算路
        RouteSearch routeSearch = new RouteSearch(MapSearchActivity.this);
        routeSearch.setRouteSearchListener(MapSearchActivity.this);
        //fromAndTo 表示起点和目的地的坐标
        RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(start, end);
        //drivingMode 驾驶模式  ：例如躲避拥堵  不走高速  速度优先等等
        RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, 0, null, null, "");
        routeSearch.calculateDriveRouteAsyn(query);

    }
}
