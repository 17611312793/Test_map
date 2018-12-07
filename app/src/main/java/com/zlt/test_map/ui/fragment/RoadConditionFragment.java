package com.zlt.test_map.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zlt.test_map.R;
import com.zlt.test_map.bean.ConditionQueryBean;
import com.zlt.test_map.ui.activity.RoadConditionQueryActivity;
import com.zlt.test_map.ui.adapter.ConditionQueryAdapter;
import com.zlt.test_map.utile.RetrofitUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * 公路状况
 */
public class RoadConditionFragment extends Fragment {


    @BindView(R.id.mianRecyclerView)
    RecyclerView mianRecyclerView;
    Unbinder unbinder;

    public RoadConditionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_road_condition, container, false);
        unbinder = ButterKnife.bind(this, view);
        initDate();
        return view;

    }

    private void initDate() {

        RetrofitUtils.getInstance().getApiService().getConditionQueryGetData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ConditionQueryBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", e.getMessage());
                    }

                    @Override
                    public void onNext(ConditionQueryBean conditionQueryBean) {
                        int code = conditionQueryBean.getCode();
                        if (code == 200) {
                            List<ConditionQueryBean.DataBean.ListBean> list = conditionQueryBean.getData().getList();
                            ConditionQueryAdapter adapter = new ConditionQueryAdapter(R.layout.item_conditionquery, list);
                            mianRecyclerView.setAdapter(adapter);
                            mianRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(getContext(), RoadConditionQueryActivity.class);
                                    intent.putExtra("id",list.get(position).getId());
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
