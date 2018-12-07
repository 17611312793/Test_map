package com.zlt.test_map.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zlt.test_map.R;

import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * 选择危害
 */
public class HarmFragment extends Fragment {

    Unbinder unbinder;

    /**
     * 养护管理——按危害
     */

    public HarmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_harm, container, false);
        return view;
    }

    @OnClick({R.id.go_back, R.id.position, R.id.type, R.id.bt_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.position:
                break;
            case R.id.type:
                break;
            case R.id.bt_sure:
                break;
        }
    }
}
