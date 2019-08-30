package com.mohan.project.easytools.file;

import com.mohan.project.easytools.common.StringTools;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * File工具类
 * @author mohan
 * @date 2018-08-29 13:15:22
 */
public class FileTools {

    /**
     * 系统换行符
     */
    public static final String LF = java.security.AccessController.doPrivileged(new sun.security.action.GetPropertyAction("line.separator"));

    /**
     * 获取指定文件内容
     * @param path 文件路径
     * @return 指定文件内容
     */
    public static Optional<List<String>> getLines(Path path) {
        try {
            return Optional.ofNullable(Files.readAllLines(path, Charset.defaultCharset()));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    /**
     * 获取指定文件内容
     * @param path 文件路径
     * @return 指定文件内容
     */
    public static Optional<String> getContent(Path path) {
        Optional<List<String>> optional = getLines(path);
        List<String> lines = optional.orElse(new ArrayList<>());
        if(lines.isEmpty()) {
            return Optional.empty();
        }
        StringBuilder content = new StringBuilder();
        for (String line : lines) {
            content.append(line).append(LF);
        }
        return Optional.of(content.toString());
    }

    /**
     * 获取banner信息
     * banner文件必须在classess目录下
     * 默认banner文件名称为banner.txt
     * @return banner信息
     */
    public static String getBanner() {
        return getBanner("banner.txt", StringTools.EMPTY);
    }

    /**
     * 获取banner信息
     * banner文件必须在classess目录下
     * @param bannerFileName banner文件名称
     * @return banner信息
     */
    public static String getBanner(String bannerFileName) {
        return getBanner(bannerFileName, StringTools.EMPTY);
    }

    /**
     * 获取banner信息
     * banner文件必须在classess目录下
     * @param bannerFileName banner文件名称
     * @param defaultBanner 默认banner
     * @return banner信息
     */
    public static String getBanner(String bannerFileName, String defaultBanner) {
        Path path = Paths.get(PathTools.getClassesPath(), bannerFileName);
        Optional<String> content = FileTools.getContent(path);
        return content.orElse(defaultBanner);
    }
}