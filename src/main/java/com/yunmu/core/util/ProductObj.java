package com.yunmu.core.util;

import java.io.Serializable;

public class ProductObj implements Serializable  {
    String productId;
    Double amount;


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
