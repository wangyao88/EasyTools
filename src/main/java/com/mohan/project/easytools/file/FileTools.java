package com.mohan.project.easytools.file;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * File工具类
 * @author mohan
 * @date 2018-08-29 13:15:22
 */
public final class FileTools {

    /**
     * 系统换行符
     */
    public static final String LF = java.security.AccessController.doPrivileged(new sun.security.action.GetPropertyAction("line.separator"));

    private FileTools() {}

    /**
     * 获取指定文件内容
     * @param path 文件路径
     * @return 指定文件内容
     */
    public static Optional<List<String>> getLines(String path) {
        try {
            File file = new File(path);
            if(!file.exists() || file.isDirectory()) {
                return Optional.empty();
            }
            return Optional.ofNullable(Files.readLines(file, Charsets.UTF_8));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    /**
     * 获取指定文件内容
     * @param path 文件路径
     * @return 指定文件内容
     */
    public static Optional<String> getContent(String path) {
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
}