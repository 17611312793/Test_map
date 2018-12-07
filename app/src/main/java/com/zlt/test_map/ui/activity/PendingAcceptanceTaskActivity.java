package com.zlt.test_map.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zlt.test_map.R;
import com.zlt.test_map.base.BaseActivity;
import com.zlt.test_map.bean.ReceiptBean;
import com.zlt.test_map.bean.TobereceivedBean;
import com.zlt.test_map.ui.adapter.TobereceivedAdapter;
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
 * 待验收任务——全选
 */

public class PendingAcceptanceTaskActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.myRecyclerView)
    RecyclerView myRecyclerView;
    @BindView(R.id.bt_next)
    Button btNext;
    private int id;
    private int condition_id;
    private int supplier_id;

    @Override
    protected int setLayout() {
        return R.layout.activity_pendingcceptanceask;
    }

    @Override
    protected void initView() {
        title.setText("待验收任务");

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        //选定的公路
        condition_id = intent.getIntExtra("condition_id", 0);
        //选定的供应商
        supplier_id = intent.getIntExtra("supplier_id", 0);
        Log.e("TAG", "condition_id:" + condition_id + "-----" + "supplier_id:" + supplier_id);

    }

    @OnClick({R.id.go_back, R.id.all_choose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.go_back:
                finish();
                break;
            case R.id.all_choose:
                break;
            case R.id.bt_next:
                RetrofitUtils.getInstance().getApiService().getReceiptPostData("256b3db5e6df54d41a1948174774c14c", id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ReceiptBean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("Error", e.getMessage());
                            }

                            @Override
                            public void onNext(ReceiptBean receiptBean) {
                                int code = receiptBean.getCode();
                                if (code == 200) {
//                                    startActivity(new Intent(PendingAcceptanceTaskActivity.this, PendingAcceptancTesk02Activity.class));
                                }
                            }
                        });
                startActivity(new Intent(PendingAcceptanceTaskActivity.this, PendingAcceptancTesk02Activity.class));

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
        RetrofitUtils.getInstance().getApiService().getTobereceivedGetData(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TobereceivedBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", e.getMessage());
                    }

                    @Override
                    public void onNext(TobereceivedBean tobereceivedBean) {
                        int code = tobereceivedBean.getCode();
                        if (code == 200) {
                            List<TobereceivedBean.DataBean.ListBean> list = tobereceivedBean.getData().getList();
                            TobereceivedAdapter adapter = new TobereceivedAdapter(R.layout.item_tobereceived, list);
                            myRecyclerView.setAdapter(adapter);
                            myRecyclerView.setLayoutManager(new LinearLayoutManager(PendingAcceptanceTaskActivity.this));
                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    id = list.get(position).getId();
                                    Log.e("TAG", id + "");
                                }
                            });

                        }
                    }
                });
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitUtils.getInstance().getApiService().getReceiptPostData(token, id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ReceiptBean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("Error", e.getMessage());
                            }

                            @Override
                            public void onNext(ReceiptBean receiptBean) {
                                int code = receiptBean.getCode();
                                if (code == 200) {
                                    ReceiptBean.DataBean.ListBean list = receiptBean.getData().getList();
                                    Intent intent = new Intent(PendingAcceptanceTaskActivity.this, PendingAcceptancTesk02Activity.class);
                                    intent.putExtra("id",id);
                                    intent.putExtra("routecode",list.getRoutecode());
                                    intent.putExtra("routeclaim",list.getRouteclaim());
                                    intent.putExtra("ingnum",list.getIngnum());
                                    intent.putExtra("position",list.getPosition());
                                    intent.putExtra("type",list.getType());
                                    intent.putExtra("area",list.getArea());
                                    startActivity(intent);

                                }
                            }
                        });
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
