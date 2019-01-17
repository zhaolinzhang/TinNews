package com.zhaolin_zhang.tinnews.save.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhaolin_zhang.tinnews.R;
import com.zhaolin_zhang.tinnews.common.TinBasicFragment;

public class SavedNewsDetailedFragment extends TinBasicFragment {

    public static SavedNewsDetailedFragment newInstance() {

        Bundle args = new Bundle();

        SavedNewsDetailedFragment fragment = new SavedNewsDetailedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_saved_news_detailed, container, false);
    }

}
