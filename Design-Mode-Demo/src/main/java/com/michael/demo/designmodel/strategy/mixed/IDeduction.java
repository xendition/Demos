package com.michael.demo.designmodel.strategy.mixed;

/**
 * 扣款策略接口
 *
 * @author Michael
 */
public interface IDeduction {

    /**
     * 扣款，提供交易和卡信息，进行扣款，并返回扣款是否成功
     */
    boolean exec(Card card, Trade trade);
}
