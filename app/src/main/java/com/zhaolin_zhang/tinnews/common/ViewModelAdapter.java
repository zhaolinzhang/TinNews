package com.zhaolin_zhang.tinnews.common;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ViewModelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<BaseViewModel> viewModels;
    private final SparseArrayCompat<BaseViewModel> viewTypeMap;

    public ViewModelAdapter() {
        viewModels = new ArrayList<>();
        viewTypeMap = new SparseArrayCompat<>();
    }

    public void addViewModels(Collection<? extends BaseViewModel> viewModels) {
        this.viewModels.clear();
        this.viewTypeMap.clear();
        addAll(viewModels);
        notifyDataSetChanged();
    }

    public void addViewModels(BaseViewModel viewModel) {
        this.viewModels.add(viewModel);
        this.viewTypeMap.put(viewModel.getViewType(), viewModel);
        int position = getPosition(viewModel);
        notifyItemInserted(position);
    }

    public void removeViewModel(int position) {
        if (position < -1 || position >= viewModels.size()) {
            return;
        }
        viewModels.remove(position);
        notifyItemRemoved(position);
    }

    public void removeViewModel(BaseViewModel viewModel) {
        int position = getPosition(viewModel);
        removeViewModel(position);
    }

    private int getPosition(BaseViewModel viewModel) {
        int position = viewModels.indexOf(viewModel);
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return viewTypeMap.get(i).createViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        viewModels.get(i).bindViewHolder(viewHolder);
    }

    @Override
    public int getItemCount() {
        return viewModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        return viewModels.get(position).getViewType();
    }

    private void addAll(Collection<? extends BaseViewModel> viewModels) {
        if (viewModels == null) {
            return;
        }

        for (BaseViewModel baseViewModel: viewModels) {
            this.viewModels.add(baseViewModel);
            viewTypeMap.put(baseViewModel.getViewType(), baseViewModel);
        }
    }

    public boolean isEmpty() {
        return viewModels.isEmpty();
    }
}
