package com.mohan.project.easytools.log;


import com.google.common.base.Throwables;
import com.mohan.project.easytools.common.DateTools;
import com.mohan.project.easytools.common.StringTools;

import java.text.MessageFormat;

/**
 * log工具类
 * @author mohan
 * @date 2018-08-30 10:22:58
 */
public class LogTools {

    private static final String INFO = "info";
    private static final String WARN = "warn";
    private static final String ERROR = "error";
    private static final String DEBUG = "debug";

    public static String info(String msg, Object... args) {
        String formatedMsg = MessageFormat.format(msg, args);
        return configureMsg(INFO, formatedMsg);
    }

    public static String warn(String msg, Object... args) {
        String formatedMsg = MessageFormat.format(msg, args);
        return configureMsg(WARN, formatedMsg);
    }

    public static String error(String msg, Object... args) {
        String formatedMsg = MessageFormat.format(msg, args);
        return configureMsg(ERROR, formatedMsg);
    }

    public static String error(String msg, Throwable throwable, Object... args) {
        String formatedMsg = MessageFormat.format(msg, args);
        String errorMsg = StringTools.append(StringTools.SPACE, formatedMsg, "完整异常信息：\n", Throwables.getStackTraceAsString(throwable));
        return configureMsg(DEBUG, errorMsg);
    }

    public static String debug(String msg, Object... args) {
        String formatedMsg = MessageFormat.format(msg, args);
        return configureMsg(DEBUG, formatedMsg);
    }

    private static String configureMsg(String level, String msg) {
        String log = StringTools.append(StringTools.SPACE, DateTools.getDateTimeString(DateTools.FORMAT_LOG), level, msg);
        System.out.println(log);
        return log;
    }

    public static void printDividingLine(int repeatNum) {
        System.out.println(StringTools.repeat("-", repeatNum));
    }
}