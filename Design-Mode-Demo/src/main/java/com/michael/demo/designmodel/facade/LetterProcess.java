package com.michael.demo.designmodel.facade;

/**
 * 写信过程接口 - 业务A
 *
 * @author Michael
 */
public interface LetterProcess {
    /**
     * 首先要写信的内容
     */
    public void writeContext(String context);

    /**
     * 其次写信封
     */
    public void fillEnvelope(String address);

    /**
     * 把信放到信封里
     */
    public void letterIntoEnvelope();

    /**
     * 然后邮递
     */
    public void sendLetter();
}
