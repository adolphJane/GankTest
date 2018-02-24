package com.magicalrice.adolph.module_girls;

import android.databinding.DataBindingUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.magicalrice.adolph.lib_common.datamodel.http.entities.GirlsData;
import com.magicalrice.adolph.module_girls.databinding.ItemGirlBinding;

import java.util.List;

/**
 * Created by Adolph on 2018/2/22.
 */

public class GirlsAdapter extends RecyclerView.Adapter<GirlsAdapter.GirlsViewHolder> {
    private List<GirlsData> girlList;
    private GirlsItemClickCallback girlsItemClickCallback;

    public GirlsAdapter(GirlsItemClickCallback callback) {
        this.girlsItemClickCallback = callback;
    }

    @Override
    public GirlsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemGirlBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.item_girl,parent,false);
        binding.setCallback(girlsItemClickCallback);
        return new GirlsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(GirlsViewHolder holder, int position) {
        holder.binding.setGirlsItem(girlList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return girlList == null ? 0 :girlList.size();
    }

    public void setGirlList(final List<GirlsData> list) {
        if (girlList == null) {
            girlList = list;
            notifyItemRangeInserted(0,girlList.size());
        } else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return girlList.size();
                }

                @Override
                public int getNewListSize() {
                    return list.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    GirlsData oldData = girlList.get(oldItemPosition);
                    GirlsData newData = list.get(newItemPosition);
                    return oldData.get_id() == newData.get_id();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    GirlsData oldData = girlList.get(oldItemPosition);
                    GirlsData newData = list.get(newItemPosition);
                    return oldData.get_id() == newData.get_id()
                            && oldData.getCreatedAt() == newData.getCreatedAt()
                            && oldData.getPublishedAt() == newData.getPublishedAt()
                            && oldData.getSource() == newData.getSource();
                }
            });
            girlList = list;
            diffResult.dispatchUpdatesTo(this);
        }
    }

    class GirlsViewHolder extends RecyclerView.ViewHolder {
        ItemGirlBinding binding;
        public GirlsViewHolder(ItemGirlBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
