package com.mohan.project.easytools.file;

import com.mohan.project.easytools.common.StringTools;

import java.util.Optional;

/**
 * Banner工具类
 * @author mohan
 * @date 2018-08-29 13:15:22
 */
public final class BannerTools {

    private BannerTools() {}

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
        try {
            String path = PathTools.getClassesPath()+bannerFileName;
            Optional<String> content = FileTools.getContent(path);
            return content.orElse(defaultBanner);
        }catch (Exception e) {
            return defaultBanner;
        }
    }
}