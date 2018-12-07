package com.zlt.test_map.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MyLocationStyle;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zlt.test_map.MainActivity;
import com.zlt.test_map.R;
import com.zlt.test_map.bean.ChooseTownBean;
import com.zlt.test_map.bean.WayTypeBean;
import com.zlt.test_map.ui.adapter.ChooseTownAdapter;
import com.zlt.test_map.ui.adapter.WayTypeAdapter;
import com.zlt.test_map.utile.RetrofitUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

//import com.amap.api.maps2d.MapView;

/***
 * 电子地图
 */

public class MapActivity extends AppCompatActivity {

    MapView mMapView = null;
    AMap aMap;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.query_line)
    View queryLine;
    @BindView(R.id.query_text)
    TextView queryText;
    @BindView(R.id.map_planning_line)
    View mapPlanningLine;
    @BindView(R.id.map_planning_text)
    TextView mapPlanningText;
    @BindView(R.id.add_line)
    View addLine;
    @BindView(R.id.add_text)
    TextView addText;
    @BindView(R.id.bt_total)
    Button btTotal;
    @BindView(R.id.mapTab)
    LinearLayout mapTab;
    @BindView(R.id.map_planning)
    LinearLayout map_planning;
    @BindView(R.id.make)
    ImageView make;
    private View inflate;
    private PopupWindow popupWindow;
    private boolean flag = false;
    private ChooseTownAdapter chooseTownAdapter;
    MyLocationStyle myLocationStyle;
    String[] permission = {Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE};
    AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener() {
        // marker 对象被点击时回调的接口
        // 返回 true 则表示接口已响应事件，否则返回false
        @Override
        public boolean onMarkerClick(Marker marker) {
            Toast.makeText(MapActivity.this, marker.getTitle() + "---" + marker.getSnippet(), Toast.LENGTH_SHORT).show();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
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
        // 绑定 Marker 被点击事件
        aMap.setOnMarkerClickListener(markerClickListener);
        initView();
    }

    private void initView() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
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

    @OnClick({R.id.GoBack, R.id.close, R.id.bt_total, R.id.query, R.id.map_planning, R.id.add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.GoBack://返回键
                if (flag == false) {
                    flag = true;
                    btTotal.setVisibility(View.VISIBLE);
                    mapTab.setVisibility(View.VISIBLE);
                    queryLine.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    mapPlanningLine.setBackgroundColor(getResources().getColor(R.color.transparent));
                    queryText.setTextColor(getResources().getColor(R.color.colorAccent));
                    mapPlanningText.setTextColor(getResources().getColor(R.color.black));
                    addLine.setBackgroundColor(getResources().getColor(R.color.transparent));
                    addText.setTextColor(getResources().getColor(R.color.black));
                    make.setImageResource(R.mipmap.make);
                } else {
                    flag = false;
                    finish();
                }
                break;
            case R.id.close://关闭当前页面
                finish();
                break;
            case R.id.bt_total://汇总
//                ChoiceTownshipDialog myDialog = new ChoiceTownshipDialog(this);
//                myDialog.show();
                inflate = LayoutInflater.from(this).inflate(R.layout.layout_dialog_choice_township, null);
                popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                RecyclerView myRecyclerView = inflate.findViewById(R.id.myRecyclerView);
                ImageView close = inflate.findViewById(R.id.close);
                TextView title = inflate.findViewById(R.id.title);
                title.setText("选择乡镇");
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                RetrofitUtils.getInstance().getApiService().getChooseTownGetData()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ChooseTownBean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("Error", e.getMessage());
                            }

                            @Override
                            public void onNext(ChooseTownBean chooseTownBean) {
                                int code = chooseTownBean.getCode();
                                if (code == 200) {
                                    List<ChooseTownBean.DataBean.ListBean> list = chooseTownBean.getData().getList();
                                    chooseTownAdapter = new ChooseTownAdapter(R.layout.item_dialog_choice_township, list);
                                    myRecyclerView.setAdapter(chooseTownAdapter);
                                    myRecyclerView.setLayoutManager(new LinearLayoutManager(MapActivity.this));
                                    chooseTownAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                            PopupWindow popupWindow1;
                                            View inflate1 = LayoutInflater.from(MapActivity.this).inflate(R.layout.layout_dialog_choice_statistical_type, null);
                                            popupWindow1 = new PopupWindow(inflate1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                            RetrofitUtils.getInstance().getApiService().getWayTypeGetData()
                                                    .subscribeOn(Schedulers.io())
                                                    .observeOn(AndroidSchedulers.mainThread())
                                                    .subscribe(new Observer<WayTypeBean>() {
                                                        @Override
                                                        public void onCompleted() {

                                                        }

                                                        @Override
                                                        public void onError(Throwable e) {
                                                            Log.e("Error", e.getMessage());
                                                        }

                                                        @Override
                                                        public void onNext(WayTypeBean wayTypeBean) {
                                                            int code = wayTypeBean.getCode();
                                                            if (code == 200) {
                                                                List<WayTypeBean.DataBean.ListBean> list = wayTypeBean.getData().getList();
                                                                WayTypeAdapter wayTypeAdapter = new WayTypeAdapter(R.layout.item_dialog_choice_statistical_type, list);
                                                                RecyclerView myRecy = inflate1.findViewById(R.id.myRecyclerView);
                                                                Button bt_next = inflate1.findViewById(R.id.bt_next);
                                                                Button bt_cancel = inflate1.findViewById(R.id.bt_cancel);
                                                                ImageView close = inflate1.findViewById(R.id.close);
                                                                close.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {
                                                                        popupWindow1.dismiss();
                                                                    }
                                                                });
                                                                bt_cancel.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {
                                                                        popupWindow1.dismiss();
                                                                    }
                                                                });
                                                                bt_next.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {
                                                                        startActivity(new Intent(MapActivity.this, RouteableActivity.class));
                                                                        popupWindow.dismiss();
                                                                        popupWindow1.dismiss();
                                                                    }
                                                                });
                                                                myRecy.setAdapter(wayTypeAdapter);
                                                                myRecy.setLayoutManager(new LinearLayoutManager(MapActivity.this));
                                                                popupWindow1.showAtLocation(inflate1, Gravity.CENTER, 0, 0);
                                                            }
                                                        }
                                                    });
                                        }
                                    });
                                }

                            }
                        });
                popupWindow.showAtLocation(inflate, Gravity.CENTER, 0, 0);
                break;
            case R.id.query://查询
                queryLine.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                mapPlanningLine.setBackgroundColor(getResources().getColor(R.color.transparent));
                queryText.setTextColor(getResources().getColor(R.color.colorAccent));
                mapPlanningText.setTextColor(getResources().getColor(R.color.black));
                addLine.setBackgroundColor(getResources().getColor(R.color.transparent));
                addText.setTextColor(getResources().getColor(R.color.black));
                break;
            case R.id.map_planning://地图规划
                mapPlanningLine.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                queryLine.setBackgroundColor(getResources().getColor(R.color.transparent));
                mapPlanningText.setTextColor(getResources().getColor(R.color.colorAccent));
                queryText.setTextColor(getResources().getColor(R.color.black));
                addLine.setBackgroundColor(getResources().getColor(R.color.transparent));
                addText.setTextColor(getResources().getColor(R.color.black));
                btTotal.setVisibility(View.GONE);
                mapTab.setVisibility(View.GONE);
                make.setImageResource(R.mipmap.make1);
                break;
            case R.id.add://新增
                addLine.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                queryLine.setBackgroundColor(getResources().getColor(R.color.transparent));
                addText.setTextColor(getResources().getColor(R.color.colorAccent));
                queryText.setTextColor(getResources().getColor(R.color.black));
                mapPlanningLine.setBackgroundColor(getResources().getColor(R.color.transparent));
                mapPlanningText.setTextColor(getResources().getColor(R.color.black));
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    @OnClick(R.id.et_search)
    public void onMapSearch() {
        startActivity(new Intent(this, MapSearchActivity.class));
    }
}

