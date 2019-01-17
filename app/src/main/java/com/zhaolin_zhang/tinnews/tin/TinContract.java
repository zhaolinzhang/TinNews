package com.zhaolin_zhang.tinnews.tin;

import com.zhaolin_zhang.tinnews.mvp.MvpContract;
import com.zhaolin_zhang.tinnews.retrofit.response.News;

import java.util.List;

public interface TinContract {

    interface View extends MvpContract.View<Presenter> {
        void showNewsCard(List<News> newsList);
    }

    interface Presenter extends MvpContract.Presenter<View, Model> {
        void showNewsCard(List<News> newsList);
    }

    interface Model extends MvpContract.Model<Presenter> {
        void fetchData();
    }
}
