package com.zhaolin_zhang.tinnews.save.detail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhaolin_zhang.tinnews.R;
import com.zhaolin_zhang.tinnews.common.BaseViewModel;
import com.zhaolin_zhang.tinnews.common.TinBasicFragment;
import com.zhaolin_zhang.tinnews.common.Util;
import com.zhaolin_zhang.tinnews.common.ViewModelAdapter;
import com.zhaolin_zhang.tinnews.retrofit.response.News;

import java.util.LinkedList;
import java.util.List;

public class SavedNewsDetailedFragment extends TinBasicFragment {

    private static final String NEWS = "news";
    private ViewModelAdapter viewModelAdapter;

    public static SavedNewsDetailedFragment newInstance(News news) {

        Bundle args = new Bundle();
        args.putParcelable(NEWS, news);
        SavedNewsDetailedFragment fragment = new SavedNewsDetailedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_news_detailed, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModelAdapter = new ViewModelAdapter();
        recyclerView.setAdapter(viewModelAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadNews(getArguments().getParcelable(NEWS));
    }

    private void loadNews(News news) {
        List<BaseViewModel> viewModels = new LinkedList<>();

        if (!Util.isStringEmpty(news.title)) {
            viewModels.add(new TitleViewModel(news.title));
        }

        if (!Util.isStringEmpty(news.author) || !Util.isStringEmpty(news.time)) {
            viewModels.add(new AuthorViewModel(news.author, news.time));
        }

        if (!Util.isStringEmpty(news.image)) {
            viewModels.add(new ImageViewModel(news.image));
        }

        if (!Util.isStringEmpty(news.description)) {
            viewModels.add(new DescriptionViewModel(news.description));
        }

        if (!Util.isStringEmpty(news.url)) {
            viewModels.add(new ReadmoreViewModel(news.url, tinFragmentManager));
        }
        viewModelAdapter.addViewModels(viewModels);
    }

}
