package com.zlt.test_map.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zlt.test_map.R;
import com.zlt.test_map.bean.RoadConditionBean;
import com.zlt.test_map.ui.activity.CollectionActivity;
import com.zlt.test_map.ui.adapter.RoadConditionAdapter;
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
 * <p>
 * 公路路况
 */
public class Highway_ConditionFragment extends Fragment {


    @BindView(R.id.mianRecyclerView)
    RecyclerView mianRecyclerView;
    Unbinder unbinder;

    public Highway_ConditionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_highway__condition, container, false);
        unbinder = ButterKnife.bind(this, view);
        initDate();
        return view;
    }

    private void initDate() {
        RetrofitUtils.getInstance().getApiService().getRoadConditionGetData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RoadConditionBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RoadConditionBean roadConditionBean) {
                        int code = roadConditionBean.getCode();
                        if (code == 200){
                            List<RoadConditionBean.DataBean.ListBean> list = roadConditionBean.getData().getList();
                            RoadConditionAdapter adapter = new RoadConditionAdapter(R.layout.item_roadcondition, list);
                            mianRecyclerView.setAdapter(adapter);
                            mianRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        int id = list.get(position).getId();
                                        Intent intent = new Intent(getContext(), CollectionActivity.class);
                                        intent.putExtra("id",id);
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
