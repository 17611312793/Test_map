package com.zlt.test_map;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.Polyline;
import com.amap.api.maps2d.model.PolylineOptions;
import com.google.gson.Gson;
import com.zlt.test_map.bean.AddBean;
import com.zlt.test_map.bean.JINGBean;
import com.zlt.test_map.http.HttpHelp;
import com.zlt.test_map.http.NetBaseStatus;
import com.zlt.test_map.http.RequestManagerImpl;
import com.zlt.test_map.utile.PermissionHelper;
import com.zlt.test_map.utile.PermissionInterface;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements PermissionInterface, LocationSource, AMapLocationListener {

    private com.amap.api.maps2d.MapView mMapView = null;
    private AMap aMap;
    private PermissionHelper mPermissionHelper;

    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;
    private LocationSource.OnLocationChangedListener mListener;
    boolean isFirstLoc = true;

    Polyline polyline;
    protected HttpHelp httpHelp;
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<FixedPointBean> keepList = new ArrayList<>();
    private Bitmap bmp;
    private Bitmap bmc;
    private String id;

    private EditText bianman;
    private EditText name;
    private EditText zhuanghao;
    private EditText leixing;
    private EditText kuandu;
    private EditText lujikuandu;

    private TextView tv_bianman;
    private TextView tv_name;
    private TextView tv_zhuanghao;
    private TextView tv_leixing;
    private TextView tv_kuandu;
    private TextView tv_lujikuandu;

    private TabLayout mmp;
    private Button button;

    private LinearLayout tv_zhans;
    private LinearLayout ed_xinix;
    private TextView title;
    private ImageView goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        httpHelp = new HttpHelp(MainActivity.this);
        mmp = findViewById(R.id.mmp);
        tv_zhans = findViewById(R.id.tv_zhans);
        ed_xinix = findViewById(R.id.ed_xinix);

        bianman = findViewById(R.id.bianman);
        name = findViewById(R.id.name);
        zhuanghao = findViewById(R.id.zhuanghao);
        leixing = findViewById(R.id.leixing);
        kuandu = findViewById(R.id.kuandu);
        lujikuandu = findViewById(R.id.lujikuandu);

        tv_bianman = findViewById(R.id.tv_bianman);
        tv_name = findViewById(R.id.tv_name);
        tv_zhuanghao = findViewById(R.id.tv_zhuanghao);
        tv_leixing = findViewById(R.id.tv_leixing);
        tv_kuandu = findViewById(R.id.tv_kuandu);
        tv_lujikuandu = findViewById(R.id.tv_lujikuandu);
        title = findViewById(R.id.title);
        goBack = findViewById(R.id.go_back);

        title.setText("增加");
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        button = findViewById(R.id.button);

        Resources res = MainActivity.this.getResources();
        bmp = BitmapFactory.decodeResource(res, R.mipmap.location1);
        bmc = BitmapFactory.decodeResource(res, R.mipmap.location);

        addData();
        //初始化并发起权限申请
        mPermissionHelper = new PermissionHelper(this, this);
        mPermissionHelper.requestPermissions();
//        Log.i("XXX", sHA1(this));
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map_view);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        mMapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mMapView.getMap();

            //设置显示定位按钮 并且可以点击
            UiSettings settings = aMap.getUiSettings();
            aMap.setLocationSource(this);//设置了定位的监听,这里要实现LocationSource接口
            // 是否显示定位按钮
            settings.setMyLocationButtonEnabled(true);
            aMap.setMyLocationEnabled(true);//显示定位层并且可以触发定位,默认是flase
            mLocationClient = new AMapLocationClient(getApplicationContext());
            //设置定位回调监听，这里要实现AMapLocationListener接口，AMapLocationListener接口只有onLocationChanged方法可以实现，用于接收异步返回的定位结果，参数是AMapLocation类型。
            mLocationClient.setLocationListener(this);
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置是否返回地址信息（默认返回地址信息）
            mLocationOption.setNeedAddress(true);
            //设置是否只定位一次,默认为false
            mLocationOption.setOnceLocation(false);
            //设置是否强制刷新WIFI，默认为强制刷新
            mLocationOption.setWifiActiveScan(true);
            //设置是否允许模拟位置,默认为false，不允许模拟位置
            mLocationOption.setMockEnable(false);
            //设置定位间隔,单位毫秒,默认为2000ms
            mLocationOption.setInterval(2000);
            //给定位客户端对象设置定位参数
            mLocationClient.setLocationOption(mLocationOption);
        }

//        List<LatLng> latLngs = new ArrayList<LatLng>();
//        for (FixedPointBean infoBean : list) {
//            //添加线集合
//            latLngs.add(new LatLng(infoBean.getLatitude(), infoBean.getLongitude()));
//            polyline = aMap.addPolyline(new PolylineOptions().
//                    addAll(latLngs).width(5).color(Color.argb(255, 255, 1, 1)));
//        }
//        if (list != null && list.size() > 0) {
//            LatLng latLng = new LatLng(list.get(0).getLatitude(), list.get(0).getLongitude());
//            MarkerOptions markerOptions = new MarkerOptions()
//                    .position(latLng)
////                    .draggable(true)
//                    .icon(BitmapDescriptorFactory.fromBitmap(bmc));
//            aMap.addMarker(markerOptions);
//
//            LatLng latLng1 = new LatLng(list.get(list.size() - 1).getLatitude(), list.get(list.size() - 1).getLongitude());
//            MarkerOptions markerOptions1 = new MarkerOptions()
//                    .position(latLng1)
////                    .draggable(true)
//                    .icon(BitmapDescriptorFactory.fromBitmap(bmp));
//            aMap.addMarker(markerOptions1);
//        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button.getText().toString().trim().equals("开始采集")) {

                    if (bianman.getText().toString().trim().isEmpty()) {
                        Toast.makeText(MainActivity.this, "请填写路线编码", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        tv_bianman.setText(bianman.getText().toString().trim());
                    }
                    if (name.getText().toString().trim().isEmpty()) {
                        Toast.makeText(MainActivity.this, "请填写路线名称", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        tv_name.setText(name.getText().toString().trim());
                    }
                    if (zhuanghao.getText().toString().trim().isEmpty()) {
                        Toast.makeText(MainActivity.this, "请填写起点桩号", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        tv_zhuanghao.setText(zhuanghao.getText().toString().trim());
                    }
                    if (leixing.getText().toString().trim().isEmpty()) {
                        Toast.makeText(MainActivity.this, "请填写路面类型", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        tv_leixing.setText(leixing.getText().toString().trim());
                    }
                    if (kuandu.getText().toString().trim().isEmpty()) {
                        Toast.makeText(MainActivity.this, "请填写路面宽度", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        tv_kuandu.setText(kuandu.getText().toString().trim());
                    }
                    if (lujikuandu.getText().toString().trim().isEmpty()) {
                        Toast.makeText(MainActivity.this, "请填写路基宽度", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        tv_lujikuandu.setText(lujikuandu.getText().toString().trim());
                    }
                    httpHelp.LogIn(101, bianman.getText().toString().trim()
                            , name.getText().toString().trim()
                            , zhuanghao.getText().toString().trim()
                            , leixing.getText().toString().trim()
                            , kuandu.getText().toString().trim()
                            , lujikuandu.getText().toString().trim()
                            , new RequestManagerImpl() {
                                @Override
                                public void onSuccess(String response, int requestType) {
                                    AddBean addBean = AddBean.objectFromData(response);
                                    id = addBean.getData().getId();
                                    mLocationClient.startLocation();
                                    Log.i("XXX", "id-" + addBean.getData().getId());
                                    button.setText("停止采集");
                                    ed_xinix.setVisibility(View.GONE);
                                    tv_zhans.setVisibility(View.VISIBLE);
                                    button.setBackgroundResource(R.drawable.bg_mmp2);
                                    Toast.makeText(MainActivity.this, "开始采集", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFail(NetBaseStatus errorMsg, int requestType) {
                                    Toast.makeText(MainActivity.this, errorMsg.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            });
                } else if (button.getText().toString().trim().equals("停止采集")) {
                    mLocationClient.stopLocation();
                    LatLng latLng1 = new LatLng(keepList.get(keepList.size() - 1).getLatitude(), keepList.get(keepList.size() - 1).getLongitude());
                    MarkerOptions markerOptions1 = new MarkerOptions()
                            .position(latLng1)
//                    .draggable(true)
                            .icon(BitmapDescriptorFactory.fromBitmap(bmp));
                    aMap.addMarker(markerOptions1);
                    JINGBean jingBean = new JINGBean();
                    jingBean.setData(keepList);
                    Log.i("XXX", "--" + new Gson().toJson(jingBean));
                    httpHelp.Add_Detail(101, id, new Gson().toJson(jingBean), new RequestManagerImpl() {
                        @Override
                        public void onSuccess(String response, int requestType) {
                            button.setBackgroundResource(R.drawable.bg_mmp3);
                        }

                        @Override
                        public void onFail(NetBaseStatus errorMsg, int requestType) {

                        }
                    });
                }

            }
        });
        findViewById(R.id.git_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                httpHelp.Getdata(111, new RequestManagerImpl() {
                    @Override
                    public void onSuccess(String response, int requestType) {
                        JINGBean jingBean = JINGBean.objectFromData(response);
                        if (jingBean.getData() == null || jingBean.getData().size() < 0) return;

                        List<LatLng> latLngs = new ArrayList<LatLng>();
                        for (FixedPointBean infoBean : jingBean.getData()) {
                            //添加线集合
                            latLngs.add(new LatLng(infoBean.getLatitude(), infoBean.getLongitude()));
                            polyline = aMap.addPolyline(new PolylineOptions().
                                    addAll(latLngs).width(5).color(Color.argb(255, 255, 1, 1)));
                        }
                        if (latLngs != null && latLngs.size() > 0) {
                            LatLng latLng = new LatLng(latLngs.get(0).latitude, latLngs.get(0).longitude);
                            MarkerOptions markerOptions = new MarkerOptions()
                                    .position(latLng)
                                    .icon(BitmapDescriptorFactory.fromBitmap(bmc));
                            aMap.addMarker(markerOptions);

                            LatLng latLng1 = new LatLng(latLngs.get(latLngs.size() - 1).latitude, latLngs.get(latLngs.size() - 1).longitude);
                            MarkerOptions markerOptions1 = new MarkerOptions()
                                    .position(latLng1)
                                    .icon(BitmapDescriptorFactory.fromBitmap(bmp));
                            aMap.addMarker(markerOptions1);
                        }

                    }

                    @Override
                    public void onFail(NetBaseStatus errorMsg, int requestType) {

                    }
                });
            }
        });
        findViewById(R.id.caiji).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    List<LatLng> latLngs = new ArrayList<LatLng>();

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                //获取定位信息
                StringBuffer buffer = new StringBuffer();
                buffer.append(aMapLocation.getLatitude() + "-"//获取纬度
                        + aMapLocation.getLongitude() + "-"//获取经度
                        + aMapLocation.getAccuracy());//获取精度信息
                keepList.add(new FixedPointBean(aMapLocation.getLongitude(), aMapLocation.getLatitude()));
                Log.i("XXX", "-" + buffer.toString());

                //添加线集合
                latLngs.add(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude()));
                polyline = aMap.addPolyline(new PolylineOptions().
                        addAll(latLngs).width(5).color(Color.argb(255, 255, 1, 1)));

                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                if (isFirstLoc) {
                    //设置缩放级别
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(18));
                    //将地图移动到定位点
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener.onLocationChanged(aMapLocation);

                    LatLng latLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                    MarkerOptions markerOptions = new MarkerOptions()
                            .position(latLng)
                            .draggable(true)
                            .icon(BitmapDescriptorFactory.fromBitmap(bmc));
                    aMap.addMarker(markerOptions);

                    isFirstLoc = false;
                }
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
                Toast.makeText(getApplicationContext(), "定位失败", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
    }

    @Override
    public void deactivate() {
        mListener = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)现地图生命周期管理
        mMapView.onSaveInstanceState(outState);
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
    public int getPermissionsRequestCode() {
        return 10000;
    }

    @Override
    public String[] getPermissions() {
        return new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION

        };
    }

    @Override
    public void requestPermissionsSuccess() {
        //已经拥有所需权限，可以放心操作任何东西了

    }

    @Override
    public void requestPermissionsFail() {

    }

    private void addData() {
        list.add("路线");
        list.add("桥梁");
        list.add("涵洞");
        list.add("渡口");
        list.add("驿站");
        list.add("农家乐");
        list.add("旅馆");
        list.add("宾馆");
        list.add("饭店");
        list.add("银行");

        for (String str : list) {
            mmp.addTab(mmp.newTab().setText(str));
        }

    }


}
