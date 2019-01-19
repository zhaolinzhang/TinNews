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
        return new TinProfileFragment();
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
        if (viewModelAdapter.isEmpty()) {
            viewModelAdapter.addViewModels(new TitleViewModel(getString(R.string.setting)
                    , R.layout.setting_title_layout));
            viewModelAdapter.addViewModels(new RowTextViewModel(getString(R.string.clear_cache),
                    presenter.getCacheClearListener()));
            viewModelAdapter.addViewModels(new TitleViewModel(getString(R.string.change_source),
                    R.layout.setting_title_layout));
            viewModelAdapter.addViewModels(new RowTextViewModel(getString(R.string.us),
                    presenter.getOnCountryChangeListener(getString(R.string.us))));
            viewModelAdapter.addViewModels(new RowTextViewModel(getString(R.string.cn),
                    presenter.getOnCountryChangeListener(getString(R.string.cn))));
        }
    }

    @Override
    public void onCacheCleared() {
        Toast.makeText(getContext(), "Cache has been cleared", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCountryChanged(String country) {
        Toast.makeText(getContext(), "Country has been changed to " + country, Toast.LENGTH_SHORT).show();
    }
}
