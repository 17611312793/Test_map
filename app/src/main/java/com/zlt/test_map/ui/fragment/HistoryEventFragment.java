package com.zlt.test_map.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zlt.test_map.R;
import com.zlt.test_map.bean.HistoryBean;
import com.zlt.test_map.utile.RetrofitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * 历史事件
 */
public class HistoryEventFragment extends Fragment {


    @BindView(R.id.myRecyclerView)
    RecyclerView myRecyclerView;
    Unbinder unbinder;

    public HistoryEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history_event, container, false);
        unbinder = ButterKnife.bind(this, view);
        initDate();
        return view;
    }

    private void initDate() {
        RetrofitUtils.getInstance().getApiService().getHistoryGetData(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HistoryBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", e.getMessage());
                    }

                    @Override
                    public void onNext(HistoryBean historyBean) {
                        int code = historyBean.getCode();
                        if (code == 200) {
                            HistoryBean.DataBean.ListBean list = historyBean.getData().getList();

                        }
                    }
                });
//        ArrayList<String> list = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            list.add("泥石流阻断交通");
//        }
//        RoadAdapter roadAdapter = new RoadAdapter(R.layout.item_history_event, list);
//        myRecyclerView.setAdapter(roadAdapter);
//        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
