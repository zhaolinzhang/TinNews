package com.zhaolin_zhang.tinnews.save;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhaolin_zhang.tinnews.R;
import com.zhaolin_zhang.tinnews.common.TinFragmentManager;
import com.zhaolin_zhang.tinnews.retrofit.response.News;
import com.zhaolin_zhang.tinnews.save.detail.SavedNewsDetailedFragment;

import java.util.LinkedList;
import java.util.List;

public class SavedNewsAdapter extends RecyclerView.Adapter<SavedNewsAdapter.SavedNewsViewHolder> {

    private List<News> newsList;
    private TinFragmentManager fragmentManager;
    private static int[] ICON_ARRAY = new int[]{R.drawable.a_news_icon, R.drawable.c_news_icon,
        R.drawable.g_news_icon, R.drawable.y_news_icon, R.drawable.m_news_icon};

    public SavedNewsAdapter(TinFragmentManager tinFragmentManager) {
        this.fragmentManager = tinFragmentManager;
        this.newsList = new LinkedList<>();

    }

    public void setNewsList(List<News> newsList) {
        this.newsList.clear();
        this.newsList.addAll(newsList);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public SavedNewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.saved_news_item, viewGroup, false);
        return new SavedNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedNewsViewHolder savedNewsViewHolder, int i) {
        News news = newsList.get(i);
        savedNewsViewHolder.author.setText(news.getAuthor());
        savedNewsViewHolder.description.setText(news.getDescription());
        savedNewsViewHolder.icon.setImageResource(getDrawable());
        savedNewsViewHolder.itemView.setOnClickListener(v -> {
            fragmentManager.doFragmentTransaction(SavedNewsDetailedFragment.newInstance(news));
        });
    }

    private @DrawableRes int getDrawable() {
        return ICON_ARRAY[(int) (Math.random() * 5)];
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class SavedNewsViewHolder extends RecyclerView.ViewHolder {
        TextView author;
        TextView description;
        ImageView icon;

        public SavedNewsViewHolder(View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.author);
            description = itemView.findViewById(R.id.description);
            icon = itemView.findViewById(R.id.image);
        }
    }
}
