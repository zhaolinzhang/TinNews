package com.zhaolin_zhang.tinnews.tin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.zhaolin_zhang.tinnews.R;
import com.zhaolin_zhang.tinnews.mvp.MvpFragment;
import com.zhaolin_zhang.tinnews.retrofit.response.News;

import java.util.List;

public class TinGalleryFragment extends MvpFragment<TinContract.Presenter>
        implements TinNewsCard.OnSwipeListener, TinContract.View {

    private SwipePlaceHolderView mSwipeView;
    public static final int SWIPE_VIEW_DISPLAY_COUNT = 3;

    public static TinGalleryFragment newInstance() {
        
        Bundle args = new Bundle();
        
        TinGalleryFragment fragment = new TinGalleryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tin_gallery, container, false);

        mSwipeView = view.findViewById(R.id.swipeView);
        mSwipeView.getBuilder()
                .setDisplayViewCount(SWIPE_VIEW_DISPLAY_COUNT)
                .setSwipeDecor(new SwipeDecor()
                .setPaddingTop(20)
                .setRelativeScale(0.01f)
                .setSwipeInMsgLayoutId(R.layout.tin_news_swipe_in_msg_view)
                .setSwipeOutMsgLayoutId(R.layout.tin_news_swipe_out_msg_view));

        view.findViewById(R.id.rejectBtn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(false);
            }
        });

        view.findViewById(R.id.acceptBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(true);
            }
        });

        return view;
    }

    @Override
    public void onLike(News news) {
        presenter.saveFavoriteNews(news);
    }

    @Override
    public void showNewsCard(List<News> newsList) {
        for (News news: newsList) {
            TinNewsCard tinNewsCard = new TinNewsCard(news, mSwipeView, this);
            mSwipeView.addView(tinNewsCard);
        }
    }

    @Override
    public void onError() {
        Toast.makeText(getContext(), "News has been inserted before", Toast.LENGTH_SHORT).show();
    }

    @Override
    public TinContract.Presenter getPresenter() {
        return new TinPresenter();
    }
}
