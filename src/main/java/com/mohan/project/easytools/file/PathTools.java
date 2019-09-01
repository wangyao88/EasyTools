package com.mohan.project.easytools.file;

import java.net.URL;

/**
 * Path(路径)工具类
 * @author mohan
 * @date 2018-08-30 09:36:59
 */
public final class PathTools {

    private PathTools() {}

    public static String getClassesPath() {
        URL resource = FileTools.class.getClassLoader().getResource("");
        return resource.getPath();
    }

}