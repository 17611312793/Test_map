package com.zlt.test_map.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zlt.test_map.R;
import com.zlt.test_map.base.BaseActivity;
import com.zlt.test_map.bean.PendingaccBean;
import com.zlt.test_map.bean.StartAcceptanceBean;
import com.zlt.test_map.ui.adapter.PendingaccAdapter;
import com.zlt.test_map.utile.RetrofitUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/***
 * 待验收任务列表
 */

public class PendingAcceptancTesk01Activity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.myRecyclerView)
    RecyclerView myRecyclerView;

    @Override
    protected int setLayout() {
        return R.layout.activity_pendingcceptanceask1;
    }

    @Override
    protected void initView() {
        title.setText("待验收任务");
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.go_back)
    public void onGoBack() {
        finish();
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
        RetrofitUtils.getInstance().getApiService().getPendingaccGetData(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PendingaccBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", e.getMessage());
                    }

                    @Override
                    public void onNext(PendingaccBean pendingaccBean) {
                        Log.e("TAG", pendingaccBean.getMsg());
                        int code = pendingaccBean.getCode();
                        if (code == 200) {
                            List<PendingaccBean.DataBean.ListBean> list = pendingaccBean.getData().getList();
                            PendingaccAdapter pendingaccAdapter = new PendingaccAdapter(R.layout.item_pending_acceptance_task, list);
                            myRecyclerView.setAdapter(pendingaccAdapter);
                            myRecyclerView.setLayoutManager(new LinearLayoutManager(PendingAcceptancTesk01Activity.this));
                            pendingaccAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Log.e("TAG", "id-------" + list.get(position).getId());
                                    RetrofitUtils.getInstance().getApiService().getStartAcceptanceGetData(list.get(position).getId()+"")
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new Observer<StartAcceptanceBean>() {
                                                @Override
                                                public void onCompleted() {

                                                }

                                                @Override
                                                public void onError(Throwable e) {
                                                    Log.e("Error", e.getMessage());
                                                }

                                                @Override
                                                public void onNext(StartAcceptanceBean startAcceptanceBean) {
                                                    int code = startAcceptanceBean.getCode();
                                                    if (code == 200) {
                                                        List<StartAcceptanceBean.DataBean.ListaBean> lista = startAcceptanceBean.getData().getLista();
                                                        StartAcceptanceBean.DataBean.ListbBean listb = startAcceptanceBean.getData().getListb();
                                                        int id = list.get(position).getId();
                                                        Intent intent = new Intent(PendingAcceptancTesk01Activity.this, PendingAcceptancTesk03Activity.class);
                                                        intent.putExtra("area", lista.get(position).getArea());
                                                        intent.putExtra("ingnum", lista.get(position).getIngnum());
                                                        intent.putExtra("images", lista.get(position).getImages());
                                                        intent.putExtra("position1", lista.get(position).getPosition());
                                                        intent.putExtra("routeclaim", lista.get(position).getRouteclaim());
                                                        intent.putExtra("routecode", lista.get(position).getRoutecode());
                                                        intent.putExtra("type", lista.get(id).getType());
//                                                        intent.putExtra("backup", listb.getBackup());
//                                                        intent.putExtra("images1", listb.getImages());
                                                        intent.putExtra("id",list.get(position).getId());
                                                        startActivity(intent);
                                                        Log.e("TAG",lista.get(position).getRouteclaim());
                                                        Log.e("TAG",list.get(position).getId()+"");
                                                        Log.e("TAG",id+"-----");

                                                    }
                                                }
                                            });
                                }
                            });
                        }
                    }
                });
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
