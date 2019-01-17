package com.zhaolin_zhang.tinnews.tin;

import com.zhaolin_zhang.tinnews.retrofit.NewsRequestApi;
import com.zhaolin_zhang.tinnews.retrofit.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TinModel implements TinContract.Model {

    private final NewsRequestApi newsRequestApi;

    public TinModel() {
        newsRequestApi = RetrofitClient.getInstance().create(NewsRequestApi.class);
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
}
