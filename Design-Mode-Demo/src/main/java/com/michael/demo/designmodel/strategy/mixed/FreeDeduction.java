package com.michael.demo.designmodel.strategy.mixed;

/**
 * 扣款策略B - 直接从自由余额中扣除
 *
 * @author Michael
 */
public class FreeDeduction implements IDeduction {

    /**
     * 自由扣款
     */
    @Override
    public boolean exec(Card card, Trade trade) {
        //直接从自由余额中扣除
        card.setFreeMoney(card.getFreeMoney() - trade.getAmount());
        return true;
    }
}
