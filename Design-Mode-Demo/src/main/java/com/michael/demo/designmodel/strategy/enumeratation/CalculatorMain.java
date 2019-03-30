package com.michael.demo.designmodel.strategy.enumeratation;

/**
 * @author Michael
 */
public class CalculatorMain {

    public static void main(String[] args) {

        int result = Calculator.ADD.exec(1,11);

        System.out.println(result);

        result = Calculator.SUB.exec(1,11);

        System.out.println(result);
    }
}
