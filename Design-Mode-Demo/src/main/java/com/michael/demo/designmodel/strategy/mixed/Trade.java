package com.michael.demo.designmodel.strategy.mixed;

/**
 * 交易类
 *
 * @author Michael
 */
public class Trade {

    /**
     * 交易编号
     */
    private String tradeNo = "";
    /**
     * 交易金额
     */
    private int amount = 0;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String postNo) {
        this.tradeNo = postNo;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
