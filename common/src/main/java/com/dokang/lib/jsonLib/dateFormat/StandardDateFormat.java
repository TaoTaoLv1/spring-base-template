package com.dokang.lib.jsonLib.dateFormat;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用于转换 'yyyy-MM-dd hh:mm:ss'格式
 *
 * @author jiabin
 */
public class StandardDateFormat extends SimpleDateFormat {
    public final static String FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    private static final long serialVersionUID = 1L;

    public StandardDateFormat() {
        super(FORMAT_DEFAULT);
    }


    @Override
    public Date parse(String source, ParsePosition pos) {
        // TODO Auto-generated method stub
        if (source != null && source.trim().length() == 10) {
            source += " 00:00:00";
        }
        return super.parse(source, pos);
    }

}
