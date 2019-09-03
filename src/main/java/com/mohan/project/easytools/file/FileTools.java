package com.mohan.project.easytools.file;

import com.mohan.project.easytools.common.StringTools;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
            return Optional.ofNullable(Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8));
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
        return Optional.of(StringTools.appendJoinLF(lines));
    }

    /**
     * 获取文件行数
     * @param filePath 文件路径
     * @return
     */
    public static int getFileLineNum(String filePath) {
        try (LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(filePath))){
            lineNumberReader.skip(Long.MAX_VALUE);
            int lineNumber = lineNumberReader.getLineNumber();
            //实际上是读取换行符数量 , 所以需要+1
            return lineNumber + 1;
        } catch (IOException e) {
            return -1;
        }
    }
}