package com.zlt.test_map.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zlt.test_map.R;
import com.zlt.test_map.bean.ConfirmBean;
import com.zlt.test_map.bean.CuringShowBean;
import com.zlt.test_map.bean.SupplierBean;
import com.zlt.test_map.ui.activity.PendingAcceptanceTaskActivity;
import com.zlt.test_map.ui.adapter.CuringShowAdapter;
import com.zlt.test_map.ui.adapter.SupplierAdapter;
import com.zlt.test_map.utile.RetrofitUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
 * 养护管理——按路线
 */
public class RouteFragment extends Fragment {

    @BindView(R.id.myRecyclerView)
    RecyclerView myRecyclerView;
    Unbinder unbinder;
    private List<CuringShowBean.DataBean.ListBean> list;
    private int condition_id;
    private int supplier_id;
    private CuringShowAdapter adapter;

    public RouteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_route, container, false);
        unbinder = ButterKnife.bind(this, view);
        initDate();
        return view;
    }

    private void initDate() {
        RetrofitUtils.getInstance().getApiService().getCuringShowGetData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CuringShowBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", e.getMessage());
                    }

                    @Override
                    public void onNext(CuringShowBean curingShowBean) {
                        int code = curingShowBean.getCode();
                        if (code == 200) {
                            list = curingShowBean.getData().getList();
                            adapter = new CuringShowAdapter(R.layout.item_route, list);
                            myRecyclerView.setAdapter(adapter);
                            myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                @Override
                                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                    CheckBox check = view.findViewById(R.id.check);
                                    condition_id = list.get(position).getId();

                                }
                            });
                        }
                    }
                });
    }

    /**
     * 下一步
     */
    @OnClick(R.id.bt_next)
    public void Next() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_dialog_place_order, null);
        PopupWindow popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        RecyclerView myRecyclerView = inflate.findViewById(R.id.myRecyclerView);
        ImageView close = inflate.findViewById(R.id.close);
        TextView title = inflate.findViewById(R.id.title);
        Button bt_place_order = inflate.findViewById(R.id.bt_place_order);
        //确认下单
        bt_place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitUtils.getInstance().getApiService().getConfirmPostData(condition_id, supplier_id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ConfirmBean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("Error", e.getMessage());
                            }

                            @Override
                            public void onNext(ConfirmBean confirmBean) {
                                int code = confirmBean.getCode();
                                if (code == 200) {
                                    List<CuringShowBean.DataBean.ListBean> data = adapter.data();
                                    if (data.isEmpty()) {
                                        Toast.makeText(getContext(),"",Toast.LENGTH_SHORT).show();
                                    }
                                    Intent intent = new Intent(getContext(), PendingAcceptanceTaskActivity.class);
                                    intent.putExtra("condition_id", condition_id);
                                    intent.putExtra("supplier_id", supplier_id);
                                    startActivity(intent);
                                    popupWindow.dismiss();
                                }
                            }
                        });

            }
        });
        title.setText("选择供应商");
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        RetrofitUtils.getInstance().getApiService().getSupplier()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SupplierBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", e.getMessage());
                    }

                    @Override
                    public void onNext(SupplierBean supplierBean) {
                        int code = supplierBean.getCode();
                        if (code == 200) {
                            List<SupplierBean.DataBean.ListBean> listBeans = supplierBean.getData().getList();
                            SupplierAdapter supplierAdapter = new SupplierAdapter(R.layout.item_dialog_choice_statistical_type, listBeans);
                            myRecyclerView.setAdapter(supplierAdapter);
                            myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            supplierAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                @Override
                                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                    supplier_id = listBeans.get(position).getId();
                                }
                            });
                        }
                    }
                });
        popupWindow.showAtLocation(inflate, Gravity.CENTER, 0, 0);
//        new PlaceOrderDialog(getContext()).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
        Log.e("TAG",token);
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
