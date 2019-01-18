package com.zhaolin_zhang.tinnews.save;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhaolin_zhang.tinnews.R;
import com.zhaolin_zhang.tinnews.common.TinBasicFragment;
import com.zhaolin_zhang.tinnews.common.ViewModelAdapter;
import com.zhaolin_zhang.tinnews.mvp.MvpFragment;
import com.zhaolin_zhang.tinnews.retrofit.response.News;
import com.zhaolin_zhang.tinnews.save.detail.SavedNewsDetailedFragment;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nullable;

public class SavedNewsFragment extends MvpFragment<SavedNewsContract.Presenter>
        implements SavedNewsContract.View {

    private ViewModelAdapter savedNewsAdapter;
    private TextView emptyState;


    public static SavedNewsFragment newInstance() {
        SavedNewsFragment fragment = new SavedNewsFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_news, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        emptyState = view.findViewById(R.id.empty_state);
        savedNewsAdapter = new ViewModelAdapter();
        recyclerView.setAdapter(savedNewsAdapter);
        return view;
    }

    @Override
    public SavedNewsContract.Presenter getPresenter() {
        return new SavedNewsPresenter();
    }

    @Override
    public void loadSavedNews(List<News> newsList) {
        if (newsList.size() == 0) {
            emptyState.setVisibility(View.VISIBLE);
        } else {
            emptyState.setVisibility(View.GONE);
        }
        if (newsList != null) {
            List<SavedNewsViewModel> models = new LinkedList<>();
            for (News news: newsList) {
                models.add(new SavedNewsViewModel(news, tinFragmentManager));
            }
            savedNewsAdapter.addViewModels(models);
        }
    }
}
