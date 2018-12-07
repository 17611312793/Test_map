package com.zlt.test_map.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.zlt.test_map.R;
import com.zlt.test_map.bean.RepairBean;
import com.zlt.test_map.ui.activity.PendingAcceptancTesk01Activity;
import com.zlt.test_map.utile.RetrofitUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * 维修信息
 */
public class MaintenanceInformationFragment extends Fragment {


    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.map)
    MapView mapView;
    Unbinder unbinder;
    @BindView(R.id.bt_submission)
    Button btSubmission;
    private AMap aMap;
    private int id;

    public MaintenanceInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maintenance_information, container, false);
        initData();
        unbinder = ButterKnife.bind(this, view);
        mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mapView.getMap();
        } else {
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
        }
        return view;
    }

    private void initData() {
        Intent intent = getActivity().getIntent();
        id = intent.getIntExtra("id", 0);
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

    @OnClick({R.id.add1, R.id.add2, R.id.add3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add1:
                break;
            case R.id.add2:
                break;
            case R.id.add3:
                break;
        }
    }


    /***
     * 注册
     */
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    /***
     * 处理事件
     * @param token
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void Token(String token) {
        btSubmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitUtils.getInstance().getApiService().getRepairPostData(token, id+"", "11.png", etContent.getText().toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<RepairBean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("Error", e.getMessage());
                            }

                            @Override
                            public void onNext(RepairBean repairBean) {
                                int code = repairBean.getCode();
                                if (code == 200) {
                                    RepairBean.DataBean data = repairBean.getData();
                                    startActivity(new Intent(getContext(), PendingAcceptancTesk01Activity.class));
                                }
                            }
                        });
            }
        });
//        startActivity(new Intent(getContext(), PendingAcceptancTesk01Activity.class));

    }

    /***
     * 解除注册
     */
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
