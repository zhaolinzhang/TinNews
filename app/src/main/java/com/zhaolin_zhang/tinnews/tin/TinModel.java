package com.zhaolin_zhang.tinnews.tin;

import android.annotation.SuppressLint;

import com.zhaolin_zhang.tinnews.TinApplication;
import com.zhaolin_zhang.tinnews.database.AppDatabase;
import com.zhaolin_zhang.tinnews.retrofit.NewsRequestApi;
import com.zhaolin_zhang.tinnews.retrofit.RetrofitClient;
import com.zhaolin_zhang.tinnews.retrofit.response.News;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TinModel implements TinContract.Model {

    private final NewsRequestApi newsRequestApi;
    private final AppDatabase db;

    public TinModel() {
        newsRequestApi = RetrofitClient.getInstance().create(NewsRequestApi.class);
        db = TinApplication.getDatabase();
    }

    private TinContract.Presenter presenter;

    @Override
    public void setPresenter(TinContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void fetchData() {
        newsRequestApi.getNewsByCountry("us")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(baseResponse -> baseResponse != null && baseResponse.articles != null)
                .subscribe(baseResponse -> {
                    presenter.showNewsCard(baseResponse.articles);
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveFavoriteNews(News news) {
        Completable.fromAction(() -> db.newsDao().insertNews(news))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {}, error -> {
                    presenter.onError();
                });
    }
}
