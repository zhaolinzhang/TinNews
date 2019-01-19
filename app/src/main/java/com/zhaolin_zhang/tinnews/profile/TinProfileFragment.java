package com.zhaolin_zhang.tinnews.profile;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhaolin_zhang.tinnews.R;
import com.zhaolin_zhang.tinnews.common.ViewModelAdapter;
import com.zhaolin_zhang.tinnews.mvp.MvpFragment;
import com.zhaolin_zhang.tinnews.save.detail.TitleViewModel;

public class TinProfileFragment extends MvpFragment<ProfileContract.Presenter>
        implements ProfileContract.View {

    private ViewModelAdapter viewModelAdapter;

    public static TinProfileFragment newInstance() {
        TinProfileFragment fragment = new TinProfileFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tin_profile, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModelAdapter = new ViewModelAdapter();
        recyclerView.setAdapter(viewModelAdapter);
        return view;
    }

    @Override
    public ProfileContract.Presenter getPresenter() {
        return new ProfilePresenter();
    }

    @Override
    public void setView() {
        viewModelAdapter.addViewModels(new TitleViewModel(getString(R.string.setting)
                , R.layout.setting_title_layout));
        viewModelAdapter.addViewModels(new RowTextViewModel(getString(R.string.clear_cache),
                presenter.getCacheClearListener()));
    }

    @Override
    public void onCacheCleared() {
        Toast.makeText(getContext(), "Cache has been cleared", Toast.LENGTH_SHORT).show();
    }
}
