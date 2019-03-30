package com.michael.demo.designmodel.strategy.enumeratation;

/**
 * 计算器
 * 策略枚举类 - 策略枚举一般担当不经常发生变化的角色
 *
 * @author Michael
 */
public enum Calculator {

    /**
     * 加法
     */
    ADD("+") {
        @Override
        public int exec(int a, int b) {
            return a + b;
        }
    },

    /**
     * 减法
     */
    SUB("-") {
        @Override
        public int exec(int a, int b) {
            return a - b;
        }
    },
    ;

    private String value;

    Calculator(String value) {
        this.value = value;
    }

    public abstract int exec(int a, int b);

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }}
