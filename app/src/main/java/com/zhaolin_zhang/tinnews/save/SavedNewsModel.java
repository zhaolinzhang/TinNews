package com.zhaolin_zhang.tinnews.save;

import android.util.Log;

import com.zhaolin_zhang.tinnews.TinApplication;
import com.zhaolin_zhang.tinnews.database.AppDatabase;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SavedNewsModel implements SavedNewsContract.Model {

    private SavedNewsContract.Presenter presenter;
    private final AppDatabase db;

    public SavedNewsModel() {
        db = TinApplication.getDatabase();
    }

    @Override
    public void setPresenter(SavedNewsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void fetchData() {
        db.newsDao().getAll().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(presenter::loadSavedNews,
                        error -> {
                            Log.e("fetchData","Fetch data error");
                            },
                        () -> {
                            Log.d("fetchData","Fetch data complete");
                        });
    }
}
