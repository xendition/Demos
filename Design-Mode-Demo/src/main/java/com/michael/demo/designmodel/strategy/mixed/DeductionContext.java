package com.michael.demo.designmodel.strategy.mixed;

/**
 * 扣款策略的封装
 *
 * @author Michael
 */
public class DeductionContext {

    /**
     * 扣款策略
     */
    private IDeduction deduction = null;

    /**
     * 构造函数传递策略
     */
    public DeductionContext(IDeduction deduction) {
        this.deduction = deduction;
    }

    /**
     * 执行扣款
     */
    public boolean exec(Card card, Trade trade) {
        return this.deduction.exec(card, trade);
    }
}
