package com.michael.demo.jdk.io;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * @author Michael
 */
public abstract class AbstractIOConstant {

    protected static String host = "localhost";
    protected static int port = 9090;

    protected static Charset charset = Charset.forName("UTF-8");
    protected static CharsetDecoder decoder = charset.newDecoder();

    protected static int bufSize = 4 * 1024;
}
