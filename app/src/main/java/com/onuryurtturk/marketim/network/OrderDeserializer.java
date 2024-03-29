package com.onuryurtturk.marketim.network;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.onuryurtturk.marketim.enums.enumOrderStateColor;
import com.onuryurtturk.marketim.model.Order;
import com.onuryurtturk.marketim.model.Orders;
import com.onuryurtturk.marketim.model.ProductDetail;
import java.lang.reflect.Type;
import java.text.DateFormatSymbols;
import java.util.Currency;
import java.util.Locale;

public class OrderDeserializer implements JsonDeserializer<Orders> {

    //currency symbol variable, appends with price value
    private String currency = Currency.getInstance(Locale.getDefault()).getSymbol();

    /**
     * Deserialize api result to order model
     * @param json - api result
     * returns Orders object which holds order list
     */
    @Override
    public Orders deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Orders orders = new Orders();
        if (json.isJsonArray()) {
            for(JsonElement element : json.getAsJsonArray()){
                JsonObject orderObject = element.getAsJsonObject();
                Order order = new Order();
                order.setDate(orderObject.get("date").getAsString());
                order.setMonth(getMonthByNumbers(orderObject.get("month").getAsString()));
                order.setMarketName(orderObject.get("marketName").getAsString());
                order.setOrderName(orderObject.get("orderName").getAsString());
                order.setProductPrice(orderObject.get("productPrice").getAsString()+currency);
                order.setProductState(orderObject.get("productState").getAsString());
                order.setStateColor(enumOrderStateColor.valueOf(orderObject.get("productState").getAsString().replace(" ","").toUpperCase(Locale.US)).toString());
                JsonObject detailObject = orderObject.getAsJsonObject("productDetail");
                order.setProductDetail(new ProductDetail(detailObject.get("orderDetail").getAsString(),detailObject.get("summaryPrice").getAsString() + currency));
                orders.addOrder(order);
            }
        }
        return orders;
    }

    /**
     * Convert month numbers to words
     * @param month - string value to convert (number to word)
     * Used in deserialize process
     */
    private String getMonthByNumbers(String month) {
        if (month.startsWith("0")) {
            month = month.replace("0", "");
        }
        return new DateFormatSymbols().getMonths()[Integer.parseInt(month) - 1];
    }

}
