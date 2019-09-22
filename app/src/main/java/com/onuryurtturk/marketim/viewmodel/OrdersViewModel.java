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

    /**
     * view model init
     */
    public void create(){
        mOrders = new Orders();
        mSelectedOrder = new MutableLiveData<>();
        mOrdersAdapter = new OrdersRecyclerAdapter(R.layout.item_order,this);
        showLoadingView = new ObservableInt(View.GONE);
        showEmptyView = new ObservableInt(View.GONE);
        checklogout = new MutableLiveData<>();
    }

    /**
     * calls api method over Orders Object
     */
    public void fetchList(){
        mOrders.fetchOrderList();
    }

    /**
     * returns order list
     */
    public MutableLiveData<List<Order>> getOrders(){
        return mOrders.getOrders();
    }

    /**
     * returns order item at specific position
     */
    public Order getOrderByIndex(Integer index) {
        if (mOrders.getOrders().getValue() != null && index != null && mOrders.getOrders().getValue().size() > index) {
            return mOrders.getOrders().getValue().get(index);
        }
        return null;
    }

    /**
     * returns adapter object
     */
    public OrdersRecyclerAdapter getOrdersAdapter(){
        return mOrdersAdapter;
    }


    /**
     * returns clicked,selected order item's value
     */
    public MutableLiveData<Order> getSelectedOrder(){
        return mSelectedOrder;
    }

    /**
     * returns current logout control variable's value
     */
    public MutableLiveData<Boolean> getLogoutStatus(){
        return checklogout;
    }

    /**
     * Sets items to the adapter and notifies that data set has been changed
     * @param orders order items to set to the adapter
     */
    public void setOrdersToAdapter(List<Order> orders){
        this.mOrdersAdapter.setOrderList(orders);
        this.mOrdersAdapter.notifyDataSetChanged();
    }

    /**
     * Item has been clicked.
     * @param index position associated with the clicked item
     */
    public void onItemClick(Integer index){
        mSelectedOrder.setValue(getOrderByIndex(index));
    }

    /**
     * Logout button has been clicked
     * set logout control value
     */
    public void onLogoutClick(){
        checklogout.setValue(true);
    }


}
