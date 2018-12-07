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
import com.zlt.test_map.bean.NewsBean;
import com.zlt.test_map.ui.activity.EventSummaryActivity;
import com.zlt.test_map.ui.activity.NewEventActivity;
import com.zlt.test_map.ui.adapter.NewsAdapter;
import com.zlt.test_map.utile.RetrofitUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * 新事件
 */
public class NewEventFragment extends Fragment {


    @BindView(R.id.myRecyclerView)
    RecyclerView myRecyclerView;
    Unbinder unbinder;

    public NewEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new, container, false);
        unbinder = ButterKnife.bind(this, view);
        initDate();
        return view;
    }

    private void initDate() {
        RetrofitUtils.getInstance().getApiService().getNewsGetData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", e.getMessage());
                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        int code = newsBean.getCode();
                        if (code == 200) {
                            List<NewsBean.DataBean.ListBean> list = newsBean.getData().getList();
                            NewsAdapter newsAdapter = new NewsAdapter(R.layout.item_event, list);
                            myRecyclerView.setAdapter(newsAdapter);
                            myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            newsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(getContext(), EventSummaryActivity.class);
                                    EventBus.getDefault().postSticky(list.get(position).getId());
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                });
//        ArrayList<String> list = new ArrayList<>();
//        for (int i = 0; i < 7; i++) {
//            list.add("泥石流阻断交通");
//        }
//        RoadAdapter roadAdapter = new RoadAdapter(R.layout.item_event, list);
//        myRecyclerView.setAdapter(roadAdapter);
//        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        roadAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(getContext(),EventSummaryActivity.class));
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.news)
    public void NewsEvent() {
        startActivity(new Intent(getContext(), NewEventActivity.class));
    }
}
