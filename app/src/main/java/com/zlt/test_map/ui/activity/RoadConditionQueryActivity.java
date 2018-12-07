package com.zlt.test_map.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_condition_query);
        ButterKnife.bind(this);
        //获取地图控件引用
        MapView mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        initView();
        initDate();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
//        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
//        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
//        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
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
