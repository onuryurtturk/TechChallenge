package com.onuryurtturk.marketim.viewmodel;

import android.view.View;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.onuryurtturk.marketim.R;
import com.onuryurtturk.marketim.adapter.OrdersRecyclerAdapter;
import com.onuryurtturk.marketim.model.Order;
import com.onuryurtturk.marketim.model.Orders;
import java.util.List;

public class OrdersViewModel extends ViewModel {

    private Orders mOrders;
    private OrdersRecyclerAdapter mOrdersAdapter;
    private MutableLiveData<Order> mSelectedOrder;
    public ObservableInt showLoadingView;
    public ObservableInt showEmptyView;
    public MutableLiveData<Boolean> checklogout;

    public void create(){
        mOrders = new Orders();
        mSelectedOrder = new MutableLiveData<>();
        mOrdersAdapter = new OrdersRecyclerAdapter(R.layout.item_order,this);
        showLoadingView = new ObservableInt(View.GONE);
        showEmptyView = new ObservableInt(View.GONE);
        checklogout = new MutableLiveData<>();
    }

    public void fetchList(){
        mOrders.fetchOrderList();
    }

    public MutableLiveData<List<Order>> getOrders(){
        return mOrders.getOrders();
    }

    public Order getOrderByIndex(Integer index) {
        if (mOrders.getOrders().getValue() != null && index != null && mOrders.getOrders().getValue().size() > index) {
            return mOrders.getOrders().getValue().get(index);
        }
        return null;
    }

    public OrdersRecyclerAdapter getOrdersAdapter(){
        return mOrdersAdapter;
    }

    public MutableLiveData<Order> getmSelectedOrder(){
        return mSelectedOrder;
    }

    public MutableLiveData<Boolean> getLogoutStatus(){
        return checklogout;
    }

    public void setOrdersToAdapter(List<Order> orders){
        this.mOrdersAdapter.setOrderList(orders);
        this.mOrdersAdapter.notifyDataSetChanged();
    }

    public void onItemClick(Integer index){
        mSelectedOrder.setValue(getOrderByIndex(index));
    }

    public void onLogoutClick(){
        checklogout.setValue(true);
    }


}
