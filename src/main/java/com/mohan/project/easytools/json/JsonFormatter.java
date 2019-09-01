package com.mohan.project.easytools.json;

import com.mohan.project.easytools.common.CharTools;
import com.mohan.project.easytools.common.StringTools;

/**
 * json格式化器
 * @author mohan
 * @date 2019-08-10 09:09:05
 */
public final class JsonFormatter {

    private JsonFormatter() {}

    /**
     * 将json字符串格式化后输出到控制台
     * @param json
     * @return
     */
    public static String formateToConsole(String json) {
        StringBuffer jsonForMatStr = new StringBuffer();
        int level = 0;
        int length = json.length();
        for(int index = 0; index < length; index++) {
            char c = json.charAt(index);
            if (level > 0  && CharTools.LF == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
                jsonForMatStr.append(StringTools.repeat(StringTools.TAB, level));
            }
            //遇到"{"和"["要增加空格和换行，遇到"}"和"]"要减少空格，以对应，遇到","要换行
            switch (c) {
                case CharTools.PREFIX_BIG_BRACKET:
                case CharTools.PREFIX_MIDDLE_BRACKET:
                    jsonForMatStr.append(c).append(StringTools.LF);
                    level++;
                    break;
                case CharTools.COMMA:
                    jsonForMatStr.append(c).append(StringTools.LF);
                    break;
                case CharTools.SUFFIX_BIG_BRACKET:
                case CharTools.SUFFIX_MIDDLE_BRACKET:
                    jsonForMatStr.append(StringTools.LF);
                    level--;
                    jsonForMatStr.append(StringTools.repeat(StringTools.TAB, level)).append(c);
                    break;
                default:
                    jsonForMatStr.append(c);
            }
        }
        return jsonForMatStr.toString();
    }
}