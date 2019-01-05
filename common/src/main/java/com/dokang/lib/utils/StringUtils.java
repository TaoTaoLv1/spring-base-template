package com.dokang.lib.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author: YwT
 * @create: 2019-01-05 14:30
 **/
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    private static final char SEPARATOR = '_';
    private static final String CHARSET_NAME = "UTF-8";

    public static String readStringFormInputStream(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        int len = 0;
        char[] buffer = new char[8 * 1024];
        while ((len = bufferedReader.read(buffer)) != -1) {
            stringBuilder.append(buffer, 0, len);
        }
        return stringBuilder.toString();
    }
}
