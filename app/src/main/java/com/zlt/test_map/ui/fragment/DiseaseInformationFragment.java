package com.zlt.test_map.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.zlt.test_map.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * 病害信息
 */
public class DiseaseInformationFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.map)
    MapView mapView;
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
    private AMap aMap;

    public DiseaseInformationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_disease_information, container, false);
        unbinder = ButterKnife.bind(this, view);
        mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mapView.getMap();
        } else

        {
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
        }
        initData();
        return view;
    }

    private void initData() {
        Intent intent = getActivity().getIntent();
        int id = intent.getIntExtra("id", 0);
        String routecode = intent.getStringExtra("routecode");
        String routeclaim = intent.getStringExtra("routeclaim");
        String ingnum = intent.getStringExtra("ingnum");
        String position = intent.getStringExtra("position");
        String type = intent.getStringExtra("type");
        String area = intent.getStringExtra("area");
        tvArea.setText(area);
        tvIngnum.setText(ingnum);
        tvPosition.setText(position);
        tvRouteclaim.setText(routeclaim);
        tvRoutecode.setText(routecode);
        tvType.setText(type);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        Log.i("sys", "mf onResume");
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     * map的生命周期方法
     */
    @Override
    public void onPause() {
        Log.i("sys", "mf onPause");
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     * map的生命周期方法
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.i("sys", "mf onSaveInstanceState");
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     * map的生命周期方法
     */
    @Override
    public void onDestroy() {
        Log.i("sys", "mf onDestroy");
        super.onDestroy();
//        mapView.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
