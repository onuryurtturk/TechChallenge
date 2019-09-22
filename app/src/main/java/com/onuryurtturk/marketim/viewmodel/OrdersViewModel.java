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

    private Orders orders;
    private OrdersRecyclerAdapter ordersAdapter;
    public MutableLiveData<Order> selectedOrder;
    public ObservableInt loading;
    public ObservableInt showEmpty;
    public MutableLiveData<Boolean> checklogout;

    public void create(){
        orders = new Orders();
        selectedOrder = new MutableLiveData<>();
        ordersAdapter = new OrdersRecyclerAdapter(R.layout.item_order,this);
        loading = new ObservableInt(View.GONE);
        showEmpty = new ObservableInt(View.GONE);
        checklogout = new MutableLiveData<>();
    }

    public void fetchList(){
        orders.fetchOrderList();
    }

    public MutableLiveData<List<Order>> getOrders(){
        return orders.getOrders();
    }

    public OrdersRecyclerAdapter getOrdersAdapter(){
        return ordersAdapter;
    }

    public void setOrdersToAdapter(List<Order> orders){
        this.ordersAdapter.setOrderList(orders);
        this.ordersAdapter.notifyDataSetChanged();
    }

    public MutableLiveData<Order> getSelectedOrder(){
        return selectedOrder;
    }

    public MutableLiveData<Boolean> getLogoutStatus(){
        return checklogout;
    }

    public void onItemClick(Integer index){
        selectedOrder.setValue(getOrderWithIndex(index));
    }

    public void onLogoutClick(){
        checklogout.setValue(true);
    }

    public Order getOrderWithIndex(Integer index) {
        if (orders.getOrders().getValue() != null && index != null && orders.getOrders().getValue().size() > index) {
            return orders.getOrders().getValue().get(index);
        }
        return null;
    }

}
