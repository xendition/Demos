package com.michael.demo.designmodel.strategy.mixed;

/**
 * 策略枚举 - 所有的策略都在这里做登记
 *
 * @author Michael
 */

public enum StrategyMan {

    /**
     * ss
     */
    SteadyDeduction("com.michael.demo.designmodel.strategy.mixed.SteadyDeduction"),

    /**
     * ss
     */
    FreeDeduction("com.michael.demo.designmodel.strategy.mixed.FreeDeduction");

    String value = "";

    StrategyMan(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
