package com.zlt.test_map.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.bumptech.glide.Glide;
import com.zlt.test_map.R;
import com.zlt.test_map.bean.QueryShowBean;
import com.zlt.test_map.utile.RetrofitUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/***
 * 路况查询
 */

public class RoadConditionQueryActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.map)
    MapView map;

    MapView mMapView = null;
    AMap aMap;
    @BindView(R.id.tv_routecode)
    TextView tvRoutecode;
    @BindView(R.id.tv_routeclaim)
    TextView tvRouteclaim;
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_ingnum)
    TextView tvIngnum;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.myViewPager)
    ViewPager myViewPager;
    @BindView(R.id.tv_size)
    TextView tvSize;
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
            Toast.makeText(RoadConditionQueryActivity.this, marker.getTitle() + "---" + marker.getSnippet(), Toast.LENGTH_SHORT).show();
            return true;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_condition_query);
        ButterKnife.bind(this);
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
        initDate();
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

    private void initDate() {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<View> view = new ArrayList<View>();
        list.add(R.mipmap.tu1);
        list.add(R.mipmap.tu2);
        list.add(R.mipmap.home_map);
        for (int i = 0; i < list.size(); i++) {
            ImageView imageView = new ImageView(this);
            Glide.with(this).load(list.get(i)).into(imageView);
            view.add(imageView);
        }
        ViewPagerAdapter adapter = new ViewPagerAdapter(view);
        myViewPager.setAdapter(adapter);

        myViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvSize.setText(position + 1 + "/" + list.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        RetrofitUtils.getInstance().getApiService().getQueryShowGetData(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QueryShowBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", e.getMessage());
                    }

                    @Override
                    public void onNext(QueryShowBean queryShowBean) {
                        int code = queryShowBean.getCode();
                        if (code == 200) {
                            QueryShowBean.DataBean.ListBean list = queryShowBean.getData().getList();
                            tvArea.setText(list.getArea());
                            tvIngnum.setText(list.getIngnum());
                            tvPosition.setText(list.getPosition());
                            tvRouteclaim.setText(list.getRouteclaim());
                            tvRoutecode.setText(list.getRoutecode());
                            tvType.setText(list.getType());
                        }
                    }
                });
    }

    private void initView() {
        title.setText("路况查询");
    }

    @OnClick(R.id.go_back)
    public void onViewClicked() {
        finish();
    }

    public class ViewPagerAdapter extends PagerAdapter {
        private List<View> mList;

        public ViewPagerAdapter(List<View> mList) {
            this.mList = mList;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         *
         * */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position));
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mList.get(position), 0);
            return mList.get(position);
        }
    }
}
