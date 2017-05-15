package com.taotao.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {

    public static String exception2String(Throwable throwable){
        // 字符串写出流，可以将写出的值进行toString
        StringWriter stringWriter = new StringWriter();
        // 文本打印流，用以包装stringwriter
        PrintWriter printWriter = new PrintWriter(stringWriter);
        //
        throwable.printStackTrace(printWriter);

        return stringWriter.toString();
    }

}
