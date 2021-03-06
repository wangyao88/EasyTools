package com.mohan.project.easytools.common;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * String相关工具类
 *
 * @author mohan
 * @date 2019-07-08
 */
public final class StringTools {

    /**
     * StringBuilder初始容量
     */
    private static final int STRING_BUILDER_SIZE = 256;
    private static final int CHARACTER_SPACE_INT_VALUE = 32;

    /**
     * 空格
     */
    public static final String SPACE = " ";

    /**
     * 空字符串
     */
    public static final String EMPTY = "";

    /**
     * \n
     */
    public static final String LF = "\n";

    /**
     * \r
     */
    public static final String CR = "\r";

    /**
     * \t
     */
    public static final String TAB = "\t";

    /**
     * ,
     */
    public static final String COMMA = ",";

    private static final String FOLDER_SEPARATOR = "/";
    private static final String WINDOWS_FOLDER_SEPARATOR = "\\";
    private static final String TOP_PATH = "..";
    private static final String CURRENT_PATH = ".";
    private static final char EXTENSION_SEPARATOR = '.';
    public static final String WAVE_LINE = "~";
    public static final String POUND = "#";
    public static final String NULL = "null";
    public static final String COLON = ":";
    public static final String DOLLAR = "$";
    public static final String UNDERLINE = "_";
    public static final String POINT = ".";
    public static final String LINE_BREAK = "\n\r";
    public static final String HR = "------------------------------------------------";

    /**
     * 未找到子串时返回的索引值
     */
    public static final int INDEX_NOT_FOUND = -1;

    /**
     * <p>The maximum size to which the padding constant(s) can expand.</p>
     */
    private static final int PAD_LIMIT = 8192;

    private StringTools() {
    }

    /**
     * 是否为空
     * null "" 为空
     *
     * @param charSequence
     * @return true为空 false不为空
     */
    public static boolean isEmpty(final CharSequence charSequence) {
        return ObjectTools.isNull(charSequence) || charSequence.length() == 0;
    }

    /**
     * 是否不为空
     *
     * @param charSequence
     * @return true不为空 false为空
     */
    public static boolean isNotEmpty(final CharSequence charSequence) {
        return !isEmpty(charSequence);
    }

    /**
     * 任意一元素为空
     *
     * @param charSequences
     * @return
     */
    public static boolean isAnyEmpty(final CharSequence... charSequences) {
        if (ArrayTools.isEmpty(charSequences)) {
            return true;
        }
        return Arrays.stream(charSequences).anyMatch(StringTools::isEmpty);
    }

    /**
     * 所有元素都不为空
     *
     * @param charSequences
     * @return
     */
    public static boolean isNoneEmpty(final CharSequence... charSequences) {
        if (ArrayTools.isEmpty(charSequences)) {
            return false;
        }
        return Arrays.stream(charSequences).allMatch(StringTools::isNotEmpty);
    }

    /**
     * 所有元素都为空
     *
     * @param charSequences
     * @return
     */
    public static boolean isAllEmpty(final CharSequence... charSequences) {
        if (ArrayTools.isEmpty(charSequences)) {
            return true;
        }
        return Arrays.stream(charSequences).allMatch(StringTools::isEmpty);
    }

    /**
     * null "" "    " true
     *
     * @param charSequence
     * @return
     */
    public static boolean isBlank(final CharSequence charSequence) {
        if (ObjectTools.isNull(charSequence) || length(charSequence) == 0) {
            return true;
        }
        return charSequence.chars().allMatch(ch -> ch == CHARACTER_SPACE_INT_VALUE);
    }

    /**
     * null "" "    " false
     *
     * @param charSequence
     * @return
     */
    public static boolean isNotBlank(final CharSequence charSequence) {
        return !isBlank(charSequence);
    }

    /**
     * 任意一元素为blank
     *
     * @param charSequences
     * @return
     */
    public static boolean isAnyBlank(final CharSequence... charSequences) {
        if (ArrayTools.isEmpty(charSequences)) {
            return true;
        }
        return Arrays.stream(charSequences).anyMatch(StringTools::isBlank);
    }

    /**
     * 所有元素都不为blank
     *
     * @param charSequences
     * @return
     */
    public static boolean isNoneBlank(final CharSequence... charSequences) {
        if (ArrayTools.isEmpty(charSequences)) {
            return false;
        }
        return Arrays.stream(charSequences).allMatch(StringTools::isNotBlank);
    }

    /**
     * 所有元素都为blank
     *
     * @param charSequences
     * @return
     */
    public static boolean isAllBlank(final CharSequence... charSequences) {
        if (ArrayTools.isEmpty(charSequences)) {
            return true;
        }
        return Arrays.stream(charSequences).allMatch(StringTools::isBlank);
    }

    /**
     * 去除空格
     * trim(null)          = null
     * trim("")            = ""
     * trim("     ")       = ""
     * trim("abc")         = "abc"
     * trim("    abc    ") = "abc"
     *
     * @param str
     * @return
     */
    public static Optional<String> trim(final String str) {
        return Optional.ofNullable(ObjectTools.isNull(str) ? null : str.trim());
    }

    /**
     * 去除空格
     * trimToEmpty(null)          = ""
     * trimToEmpty("")            = ""
     * trimToEmpty("     ")       = ""
     * trimToEmpty("abc")         = "abc"
     * trimToEmpty("    abc    ") = "abc"
     *
     * @param str
     * @return
     */
    public static String trimToEmpty(final String str) {
        return ObjectTools.isNull(str) ? EMPTY : str.trim();
    }

    /**
     * 去除空格
     * trimToEmpty(null)          = ""
     * trimToEmpty("")            = ""
     * trimToEmpty("     ")       = ""
     * trimToEmpty(" ab c")         = "abc"
     * trimToEmpty("    a  b   c    ") = "abc"
     *
     * @param str
     * @return
     */
    public static String trimAll(final String str) {
        String temp = ObjectTools.isNull(str) ? EMPTY : str.trim();
        int[] ints = temp.chars().filter(ch -> ch != CHARACTER_SPACE_INT_VALUE).toArray();
        if (ints == null || ints.length == 0) {
            return EMPTY;
        }
        int length = ints.length;
        char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            chars[i] = (char) ints[i];
        }
        return String.valueOf(chars);
    }

    /**
     * 获取字符串长度
     *
     * @param charSequence
     * @return 字符串长度 null返回0
     */
    public static int length(final CharSequence charSequence) {
        return ObjectTools.isNull(charSequence) ? 0 : charSequence.length();
    }

    /**
     * 是否相等
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(final String str1, final String str2) {
        if (ObjectTools.isNull(str1) || ObjectTools.isNull(str2)) {
            return false;
        }
        if (str1.length() != str2.length()) {
            return false;
        }
        return str1.equals(str2);
    }

    /**
     * 是否相等 忽略大小写
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equalsIgnoreCase(final String str1, final String str2) {
        if (ObjectTools.isNull(str1) || ObjectTools.isNull(str2)) {
            return false;
        }
        if (str1.length() != str2.length()) {
            return false;
        }
        return str1.toLowerCase().equals(str2.toLowerCase());
    }

    /**
     * 搜索子串索引位置 未找到返回-1
     *
     * @param seq
     * @param searchChar
     * @return
     */
    public static int indexOf(final String seq, final int searchChar) {
        if (isEmpty(seq)) {
            return INDEX_NOT_FOUND;
        }
        return seq.indexOf(searchChar);
    }

    /**
     * 搜索子串索引位置 未找到返回-1
     *
     * @param seq
     * @param searchChar
     * @return
     */
    public static int indexOf(final String seq, final char searchChar) {
        if (isEmpty(seq)) {
            return INDEX_NOT_FOUND;
        }
        return seq.indexOf(searchChar);
    }

    /**
     * 搜索子串索引位置 未找到返回-1
     *
     * @param seq
     * @param searchStr
     * @return
     */
    public static int indexOf(final String seq, final String searchStr) {
        if (isEmpty(seq) || isEmpty(searchStr)) {
            return INDEX_NOT_FOUND;
        }
        return seq.indexOf(searchStr);
    }

    /**
     * 搜索子串索引位置 未找到返回-1
     *
     * @param seq
     * @param searchStr
     * @return
     */
    public static int indexOfIgnoreCase(final String seq, final String searchStr) {
        if (isEmpty(seq) || isEmpty(searchStr)) {
            return INDEX_NOT_FOUND;
        }
        return seq.toLowerCase().indexOf(searchStr.toLowerCase());
    }

    /**
     * <p>Repeat a String {@code repeat} times to form a
     * new String.</p>
     *
     * <pre>
     * repeat(null, 2) = null
     * repeat("", 0)   = ""
     * repeat("", 2)   = ""
     * repeat("a", 3)  = "aaa"
     * repeat("ab", 2) = "abab"
     * repeat("a", -2) = ""
     * </pre>
     *
     * @param str    the String to repeat, may be null
     * @param repeat number of times to repeat str, negative treated as zero
     * @return a new String consisting of the original String repeated,
     * {@code null} if null String input
     */
    public static String repeat(final String str, final int repeat) {
        if (str == null) {
            return null;
        }
        if (repeat <= 0) {
            return EMPTY;
        }
        final int inputLength = str.length();
        if (repeat == 1 || inputLength == 0) {
            return str;
        }
        if (inputLength == 1 && repeat <= PAD_LIMIT) {
            return repeat(str.charAt(0), repeat);
        }

        final int outputLength = inputLength * repeat;
        switch (inputLength) {
            case 1:
                return repeat(str.charAt(0), repeat);
            case 2:
                final char ch0 = str.charAt(0);
                final char ch1 = str.charAt(1);
                final char[] output2 = new char[outputLength];
                for (int i = repeat * 2 - 2; i >= 0; i--, i--) {
                    output2[i] = ch0;
                    output2[i + 1] = ch1;
                }
                return new String(output2);
            default:
                final StringBuilder buf = new StringBuilder(outputLength);
                for (int i = 0; i < repeat; i++) {
                    buf.append(str);
                }
                return buf.toString();
        }
    }

    /**
     * <p>Returns padding using the specified delimiter repeated
     * to a given length.</p>
     *
     * <pre>
     * repeat('e', 0)  = ""
     * repeat('e', 3)  = "eee"
     * repeat('e', -2) = ""
     * </pre>
     *
     * <p>Note: this method does not support padding with
     * <a href="http://www.unicode.org/glossary/#supplementary_character">Unicode Supplementary Characters</a>
     * as they require a pair of {@code char}s to be represented.
     * If you are needing to support full I18N of your applications
     * consider using {@link #repeat(String, int)} instead.
     * </p>
     *
     * @param ch     character to repeat
     * @param repeat number of times to repeat char, negative treated as zero
     * @return String with repeated character
     * @see #repeat(String, int)
     */
    public static String repeat(final char ch, final int repeat) {
        if (repeat <= 0) {
            return EMPTY;
        }
        final char[] buf = new char[repeat];
        for (int i = repeat - 1; i >= 0; i--) {
            buf[i] = ch;
        }
        return new String(buf);
    }

    public static String append(String separator, String... strs) {
        List<String> list = Arrays.stream(strs).map(str -> isEmpty(str) ? EMPTY : str).collect(Collectors.toList());
        return Joiner.on(separator).join(list);
    }

    public static String append(String separator, Collection<String> strs) {
        if (CollectionTools.isEmpty(strs)) {
            return EMPTY;
        }
        List<String> list = strs.stream().map(str -> isBlank(str) ? EMPTY : str).collect(Collectors.toList());
        return Joiner.on(separator).join(list);
    }

    public static String appendJoinUnderLine(String... strs) {
        return append(UNDERLINE, strs);
    }

    public static String appendJoinEmpty(String... strs) {
        return append(EMPTY, strs);
    }

    public static String appendJoinComma(String... strs) {
        return append(COMMA, strs);
    }

    public static String appendJoinComma(Collection<String> strs) {
        return append(COMMA, strs);
    }

    public static String appendJoinPoint(Collection<String> strs) {
        return append(POINT, strs);
    }

    public static String appendJoinCommaFiltedEmptyElement(Collection<String> strs) {
        if (CollectionTools.isEmpty(strs)) {
            return EMPTY;
        }
        List<String> list = strs.stream().filter(StringTools::isNotBlank).collect(Collectors.toList());
        return Joiner.on(COMMA).join(list);
    }

    public static String appendJoinPound(String... strs) {
        return append(POUND, strs);
    }

    public static String appendJoinFileSeparator(String... strs) {
        return append(File.separator, strs);
    }

    public static String appendJoinPound(Collection<String> strs) {
        return append(POUND, strs);
    }

    public static String appendJoinLF(Collection<String> strs) {
        return append(LF, strs);
    }

    public static List<String> splitPoundToList(String str) {
        return splitWithSeparator(POUND, str);
    }

    public static Set<String> splitPoundToSet(String str) {
        return Sets.newHashSet(splitWithSeparator(POUND, str));
    }

    public static List<String> splitComma(String str) {
        return splitWithSeparator(COMMA, str);
    }

    public static List<String> splitWithSeparator(String separator, String str) {
        if (isBlank(str)) {
            return Lists.newArrayList();
        }
        return Splitter.on(separator).splitToList(str);
    }

    /**
     * 首字母转大写
     *
     * @param s
     * @return
     */
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 首字母转小写
     *
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    public synchronized static void printObject(Object object) {
        if (ObjectTools.isNull(object)) {
            return;
        }
        try {
            StringBuilder msg = new StringBuilder(object.getClass().getSimpleName());
            msg.append(" = [");
            Field[] declaredFields = object.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                msg.append(declaredField.getName())
                        .append(" = ")
                        .append(declaredField.get(object))
                        .append(COMMA)
                        .append(SPACE);
            }
            msg.append("]");
            System.out.println(msg.toString());
        } catch (Exception e) {
            // ignore
        }
    }
}