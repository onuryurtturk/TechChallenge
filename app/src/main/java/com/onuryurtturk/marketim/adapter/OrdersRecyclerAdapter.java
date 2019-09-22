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

public class OrdersRecyclerAdapter extends RecyclerView.Adapter<OrdersRecyclerAdapter.OrderViewHolder> {

    //Recyclerview adapter class
    private int layoutId;
    private List<Order> orders;
    private OrdersViewModel viewModel;

    public OrdersRecyclerAdapter(@LayoutRes int layoutId, OrdersViewModel ordersViewModel){
        this.layoutId = layoutId;
        this.viewModel = ordersViewModel;

    }

    /**
     * returns resource id
     */
    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }

    /**
     * binding init, create viewholders
     */
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);
        return new OrderViewHolder(binding);
    }

    /**
     * binding operations
     * @param holder - view holder
     * @param position - specific row position
     */
    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.bind(viewModel, position);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    /**
     * Filling order list
     */
    public void setOrderList(List<Order> orders) {
        this.orders = orders;
    }

    /**
     * returns order size
     */
    @Override
    public int getItemCount() {
        return orders == null ? 0 : orders.size();
    }


    // generating viewholders for order items
    class OrderViewHolder extends RecyclerView.ViewHolder {

        final ViewDataBinding binding;

        OrderViewHolder(ViewDataBinding binding) {
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
