package com.michael.demo.designmodel.facade;

/**
 * 信件检查类 - 用户无感知
 *
 * @author Michael
 */
public class Police {

    /**
     * 检查信件，检查完毕后警察在信封上盖个戳：此信无病毒
     */
    public void checkLetter(LetterProcess letterProcess) {
        System.out.println(letterProcess + " 信件已经检查过了...");
    }
}
