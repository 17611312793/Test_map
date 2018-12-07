package com.zlt.test_map.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zlt.test_map.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * 实时处置
 */
public class RealTimeManagementFragment extends Fragment {


    @BindView(R.id.message)
    TextView message;
    Unbinder unbinder;

    public RealTimeManagementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_real_time_management, container, false);
        unbinder = ButterKnife.bind(this, view);
        initDate();
        return view;
    }

    private void initDate() {
        // 开始走马灯效果
        message.setSelected(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
