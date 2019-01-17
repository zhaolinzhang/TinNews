package com.zhaolin_zhang.tinnews.save;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhaolin_zhang.tinnews.R;
import com.zhaolin_zhang.tinnews.common.TinBasicFragment;
import com.zhaolin_zhang.tinnews.mvp.MvpFragment;
import com.zhaolin_zhang.tinnews.retrofit.response.News;
import com.zhaolin_zhang.tinnews.save.detail.SavedNewsDetailedFragment;

import java.util.List;

public class SavedNewsFragment extends MvpFragment<SavedNewsContract.Presenter>
        implements SavedNewsContract.View {

    private TextView author;
    private TextView description;


    public static SavedNewsFragment newInstance() {
        SavedNewsFragment fragment = new SavedNewsFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_news, container, false);
        TextView textView = view.findViewById(R.id.text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinFragmentManager.doFragmentTransaction(SavedNewsDetailedFragment.newInstance());
            }
        });
        return view;
    }

    @Override
    public SavedNewsContract.Presenter getPresenter() {
        return new SavedNewsPresenter();
    }

    @Override
    public void loadSavedNews(List<News> newsList) {
        if (newsList.size() > 0) {
            News news = newsList.get(newsList.size() - 1);
            author.setText(news.getAuthor());
            description.setText(news.getDescription());
        }
    }
}
