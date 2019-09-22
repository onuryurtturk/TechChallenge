package com.onuryurtturk.marketim.model;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;

import com.onuryurtturk.marketim.network.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Orders extends BaseObservable {

    //order list holds orders
    private List<Order> orderList = new ArrayList<>();

    //Live order list to handle changes
    private MutableLiveData<List<Order>> orders = new MutableLiveData<>();

    /**
     * Adds order object to current list
     * @param od - order object to add list
     */
    public void addOrder(Order od) {
        orderList.add(od);
    }

    /**
     * returns orders object
     */
    public MutableLiveData<List<Order>> getOrders() {
        return orders;
    }

    /**
     * calls api method and fills orders object
     */
    public void fetchOrderList() {
        Callback<Orders> callback = new Callback<Orders>() {
            @Override
            public void onResponse(Call<Orders> call, Response<Orders> response) {
                Orders body = response.body();
                orders.setValue(body.orderList);
            }

            @Override
            public void onFailure(Call<Orders> call, Throwable t) {
                Log.e("Failed", t.getMessage(), t);
            }
        };

        Api.getApi().getOrders().enqueue(callback);
    }

}
