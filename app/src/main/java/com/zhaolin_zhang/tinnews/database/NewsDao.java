package com.zhaolin_zhang.tinnews.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.zhaolin_zhang.tinnews.retrofit.response.News;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface NewsDao {

    @Insert
    void insertNews(News news);

    @Query("SELECT * FROM news")
    Flowable<List<News>> getAll();
}
