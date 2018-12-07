package com.zlt.test_map.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zlt.test_map.R;
import com.zlt.test_map.bean.SurveyBean;
import com.zlt.test_map.ui.activity.EndEventActivity;
import com.zlt.test_map.utile.RetrofitUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
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
 * 事件概况
 */
public class EventSummaryFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.myViewPager)
    ViewPager myViewPager;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_site)
    TextView tvSite;
    @BindView(R.id.tv_conditions)
    TextView tvConditions;
    @BindView(R.id.tv_size)
    TextView tvSize;

    public EventSummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_summary, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<View> view = new ArrayList<View>();
        list.add(R.mipmap.tu1);
        list.add(R.mipmap.tu2);
        list.add(R.mipmap.home_map);
        for (int i = 0; i < list.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            Glide.with(this).load(list.get(i)).into(imageView);
            view.add(imageView);
        }
        ViewPagerAdapter adapter = new ViewPagerAdapter(view);
        myViewPager.setAdapter(adapter);

        myViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvSize.setText(position + 1 + "/" + list.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
     * @param id
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void Token(int id) {
        RetrofitUtils.getInstance().getApiService().getSurveyGetData(id + "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SurveyBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", e.getMessage());
                    }

                    @Override
                    public void onNext(SurveyBean surveyBean) {
                        int code = surveyBean.getCode();
                        if (code == 200) {
                            SurveyBean.DataBean.ListBean list = surveyBean.getData().getList();
                            tvTitle.setText(list.getTitle());
                            tvConditions.setText(list.getConditions());
                            tvSite.setText(list.getSite());

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.bt_disposal_completed)
    public void onCompleted() {
        startActivity(new Intent(getContext(), EndEventActivity.class));
    }

    public class ViewPagerAdapter extends PagerAdapter {
        private List<View> mList;

        public ViewPagerAdapter(List<View> mList) {
            this.mList = mList;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         *
         * */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position));
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mList.get(position), 0);
            return mList.get(position);
        }
    }

}
