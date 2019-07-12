package com.yunmu.core.util;

import java.io.Serializable;

public class ParamVo implements Serializable  {
    String payId;
    String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }
}
