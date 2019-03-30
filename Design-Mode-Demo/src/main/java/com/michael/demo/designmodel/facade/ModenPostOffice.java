package com.michael.demo.designmodel.facade;

/**
 * 现代化邮局 - 对外的门面A
 *
 * @author Michael
 */
public class ModenPostOffice {

    private LetterProcess letterProcess = new LetterProcessImpl();

    private Police letterPolice = new Police();

    private Shopping shopping = new ShoppingImpl();

    /**
     * 写信，封装，投递，一体化
     */
    public void sendLetter(String context, String address) {

        //帮你写信
        letterProcess.writeContext(context);
        //写好信封
        letterProcess.fillEnvelope(address);
        //警察要检查信件了 - 门面内部功能扩展
        letterPolice.checkLetter(letterProcess);
        //把信放到信封中
        letterProcess.letterIntoEnvelope();
        //邮递信件
        letterProcess.sendLetter();
    }

    /**
     * 邮局开展新的业务 - 代客人购物
     */
    public void buy(String something) {
        System.out.println("邮局代购物品中...");
        shopping.buy(something);
    }
}
