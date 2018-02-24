package com.magicalrice.adolph.module_news;

import android.databinding.DataBindingUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.magicalrice.adolph.lib_common.datamodel.http.entities.NewsData;
import com.magicalrice.adolph.module_news.databinding.ItemNewsBinding;

import java.util.List;

/**
 * Created by Adolph on 2018/2/23.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<NewsData> newsList;
    private NewsItemClickCallback callback;

    public NewsAdapter(NewsItemClickCallback callback) {
        this.callback = callback;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemNewsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_news,parent,false);
        return new NewsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.binding.setNewsItem(newsList.get(position));
        holder.binding.setCallback(callback);
    }

    public void setNewsList(final List<NewsData> list) {
        if (newsList == null) {
            newsList = list;
            notifyItemRangeInserted(0,newsList.size());
        } else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return newsList.size();
                }

                @Override
                public int getNewListSize() {
                    return list.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    NewsData oldData = newsList.get(oldItemPosition);
                    NewsData newData = list.get(newItemPosition);
                    return oldData.get_id() == newData.get_id();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    NewsData newData = newsList.get(oldItemPosition);
                    NewsData oldData = list.get(newItemPosition);
                    return newData.get_id() == oldData.get_id()
                            && newData.getCreatedAt() == oldData.getCreatedAt()
                            && newData.getPublishedAt() == oldData.getPublishedAt()
                            && newData.getSource() == oldData.getSource();
                }
            });
            newsList = list;
            diffResult.dispatchUpdatesTo(this);
        }
    }

    @Override
    public int getItemCount() {
        return newsList == null ? 0 : newsList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{
        ItemNewsBinding binding;
        public NewsViewHolder(ItemNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
