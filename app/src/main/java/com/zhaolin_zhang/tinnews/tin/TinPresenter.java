package com.zhaolin_zhang.tinnews.tin;

import com.zhaolin_zhang.tinnews.retrofit.response.News;

import java.util.List;

public class TinPresenter implements TinContract.Presenter {

    private TinContract.View view;

    private TinContract.Model model;

    public TinPresenter() {
        this.model = new TinModel();
        this.model.setPresenter(this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onViewAttached(TinContract.View view) {
        this.view = view;
        this.model.fetchData();
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }

    @Override
    public void showNewsCard(List<News> newsList) {
        if (this.view != null) {
            view.showNewsCard(newsList);
        }
    }
}
