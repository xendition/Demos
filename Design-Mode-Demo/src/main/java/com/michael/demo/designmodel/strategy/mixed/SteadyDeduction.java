package com.michael.demo.designmodel.strategy.mixed;

/**
 * 扣款策略A - 固定金额和自由金额各扣除50%
 *
 * @author Michael
 */
public class SteadyDeduction implements IDeduction {

    @Override
    public boolean exec(Card card, Trade trade) {
        //固定金额和自由金额各扣除50%
        int halfMoney = (int) Math.rint(trade.getAmount() / 2.0);
        card.setFreeMoney(card.getFreeMoney() - halfMoney);
        card.setSteadyMoney(card.getSteadyMoney() - halfMoney);
        return true;
    }
}
