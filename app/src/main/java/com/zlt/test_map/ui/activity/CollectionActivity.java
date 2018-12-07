package com.zlt.test_map.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.zlt.test_map.R;
import com.zlt.test_map.bean.CollectionBean;
import com.zlt.test_map.bean.RoadConditionAddBean;
import com.zlt.test_map.ui.adapter.AddAdapter;
import com.zlt.test_map.utile.RetrofitUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/***
 * 路况采集
 */

public class CollectionActivity extends AppCompatActivity {

    @BindView(R.id.go_back)
    ImageView goBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.Continue)
    TextView Continue;
    MapView mMapView = null;
    AMap aMap;
    @BindView(R.id.ed_routecode)
    EditText edRoutecode;
    @BindView(R.id.ed_routeclaim)
    EditText edRouteclaim;
    @BindView(R.id.myRecyclerView)
    RecyclerView myRecyclerView;
    @BindView(R.id.ed_position)
    EditText edPosition;
    @BindView(R.id.ed_type)
    EditText edType;
    @BindView(R.id.ed_ingnum)
    EditText edIngnum;
    @BindView(R.id.ed_area)
    EditText edArea;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
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
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        RetrofitUtils.getInstance().getApiService().getCollectionGetData(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CollectionBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", e.getMessage());
                    }

                    @Override
                    public void onNext(CollectionBean collectionBean) {
                        int code = collectionBean.getCode();
                        if (code == 200) {
                            CollectionBean.DataBean.ListBean list = collectionBean.getData().getList();
                            edRoutecode.setText(list.getRoutecode());
                            edRouteclaim.setText(list.getRouteclaim());
                        }
                    }
                });
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.mipmap.collection_add);
        list.add(R.mipmap.collection_add);
        list.add(R.mipmap.collection_add);
        AddAdapter addAdapter = new AddAdapter(R.layout.item_road, list);
        myRecyclerView.setAdapter(addAdapter);
        myRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

    }

    private void initView() {
        Continue.setVisibility(View.VISIBLE);
        title.setText("路况采集");
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


    @OnClick({R.id.go_back, R.id.Continue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.go_back:
                finish();
                break;
            case R.id.Continue:
                RetrofitUtils.getInstance().getApiService().getRoadConditionAddPostData(id,
                        edRoutecode.getText().toString(),
                        edRouteclaim.getText().toString(),
                        edIngnum.getText().toString(),
                        edPosition.getText().toString(),
                        edType.getText().toString(),
                        edArea.getText().toString(), "")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<RoadConditionAddBean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("Error", e.getMessage());
                            }

                            @Override
                            public void onNext(RoadConditionAddBean roadConditionAddBean) {
                                int code = roadConditionAddBean.getCode();
                                if (code == 200) {
                                    finish();
                                }
                            }
                        });

                break;
        }
    }
}
