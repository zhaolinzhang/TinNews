package com.zhaolin_zhang.tinnews.common;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhaolin_zhang.tinnews.R;

import java.util.UUID;

public class TinBasicFragment extends Fragment {

    protected TinFragmentManager tinFragmentManager;
    private final String uuid = UUID.randomUUID().toString();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        tinFragmentManager = (TinFragmentManager) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tin_basic, container, false);
    }

    public String getFragmentTag() {
        return this.getClass().getName() + uuid;
    }

}
