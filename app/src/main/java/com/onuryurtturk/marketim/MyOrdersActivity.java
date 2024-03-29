package com.onuryurtturk.marketim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import com.onuryurtturk.marketim.databinding.ActivityMyordersBinding;
import com.onuryurtturk.marketim.model.Order;
import com.onuryurtturk.marketim.util.LoginHelper;
import com.onuryurtturk.marketim.viewmodel.OrdersViewModel;
import java.util.List;

public class MyOrdersActivity extends AppCompatActivity {

    private OrdersViewModel ordersViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBindings(savedInstanceState);
    }

    /**
     * init bindings and set viewmodel
     */

    private void initBindings(Bundle savedInstanceState){
        ActivityMyordersBinding activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_myorders);
        ordersViewModel = ViewModelProviders.of(this).get(OrdersViewModel.class);
        if (savedInstanceState == null) {
            ordersViewModel.create();
        }
        activityBinding.setViewModel(ordersViewModel);
        initRecyclerView();
        initLogoutClick();
    }

    /**
     * fetch orderlist and bind order items to recyclerview
     * Show Emptyview if result size is zero
     * Show Loadingview while fetching
     */

    private void initRecyclerView(){
        ordersViewModel.showLoadingView.set(View.VISIBLE);
        ordersViewModel.fetchList();
        ordersViewModel.getOrders().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                ordersViewModel.showLoadingView.set(View.GONE);
                if(orders.size() == 0){
                    ordersViewModel.showEmptyView.set(View.VISIBLE);
                }else{
                    ordersViewModel.showEmptyView.set(View.GONE);
                    ordersViewModel.setOrdersToAdapter(orders);
                }
            }
        });
        initItemClick();
    }


    /**
     * init selected order observer for expansion operations
     * When item clicked, selected item and state will be changed.
     */

    private void initItemClick(){
        ordersViewModel.getSelectedOrder().observe(this, new Observer<Order>() {
            @Override
            public void onChanged(Order order) {
                if(order!=null){
                    order.setExpanded(!order.isExpanded());
                }
            }
        });
    }

    /**
     * init logout button click handler
     * When button clicked user will be warned with dialog
     */
    private void initLogoutClick(){
        ordersViewModel.getLogoutStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean logoutClicked) {
                if(logoutClicked){
                    askForLogout();
                }
            }
        });

    }

    /**
     * create and show logout confirmation dialog
     * When positive button click, user data delete by LoginHelper object
     */
    private void askForLogout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MyOrdersActivity.this);
        builder.setMessage(getResources().getString(R.string.logout_question));
        builder.setNegativeButton(getResources().getString(R.string.logout_negative), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ordersViewModel.checklogout.setValue(false);
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(getResources().getString(R.string.logout_positive), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new LoginHelper().deleteStoredUser(MyOrdersActivity.this);
                MyOrdersActivity.this.finish();
            }
        });

        builder.show();
    }
}
