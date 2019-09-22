package com.onuryurtturk.marketim.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.onuryurtturk.marketim.BR;
import com.onuryurtturk.marketim.model.Order;
import com.onuryurtturk.marketim.viewmodel.OrdersViewModel;

import java.util.List;

public class OrdersRecyclerAdapter extends RecyclerView.Adapter<OrdersRecyclerAdapter.GenericViewHolder> {

    private int layoutId;
    private List<Order> orders;
    private OrdersViewModel viewModel;

    public OrdersRecyclerAdapter(@LayoutRes int layoutId, OrdersViewModel ordersViewModel){
        this.layoutId = layoutId;
        this.viewModel = ordersViewModel;

    }

    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }

    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);
        return new GenericViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {
        holder.bind(viewModel, position);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    public void setOrderList(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public int getItemCount() {
        return orders == null ? 0 : orders.size();
    }

    class GenericViewHolder extends RecyclerView.ViewHolder {
        final ViewDataBinding binding;

        GenericViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(OrdersViewModel viewModel, Integer position) {
            binding.setVariable(BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }

    }
}
