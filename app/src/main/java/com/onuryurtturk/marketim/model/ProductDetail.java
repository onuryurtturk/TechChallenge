package com.onuryurtturk.marketim.model;

import com.google.gson.annotations.SerializedName;

public class ProductDetail {

    /**
     * Expandable layout, product details model class
     */

    @SerializedName("orderDetail")
    private String orderDetail;
    @SerializedName("summaryPrice")
    private String summaryPrice;

    public ProductDetail(String mOrderDetail, String mSummaryPrice) {
        this.orderDetail = mOrderDetail;
        this.summaryPrice = mSummaryPrice;
    }

    public String getOrderDetail() {
        return orderDetail;
    }

    public String getSummaryPrice() {
        return summaryPrice;
    }

    public void setOrderDetail(String mOrderDetail) {
        this.orderDetail = mOrderDetail;
    }

    public void setSummaryPrice(String mSummaryPrice) {
        this.summaryPrice = mSummaryPrice;
    }
}
