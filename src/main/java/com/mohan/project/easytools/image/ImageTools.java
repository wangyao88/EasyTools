package com.mohan.project.easytools.image;

import lombok.Cleanup;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 图片相关工具类
 * @author mohan
 * @date 2019-07-19
 */
public class ImageTools {

    /**
     * 获取文件系统中的图片信息
     * @param path
     * @return
     */
    public byte[] getImageByPath(String path) {
        byte[] buffer = "".getBytes();
        try {
            File file = new File(path);
            @Cleanup
            FileInputStream fis = new FileInputStream(file);
            @Cleanup
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            buffer = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * 获取文件系统的图片，返回到页面显示
     * @param request
     * @param response
     * @throws IOException
     */
    public void getImageByPath(HttpServletRequest request, HttpServletResponse response) throws IOException {
        @Cleanup
        ServletOutputStream out = response.getOutputStream();
        String path = request.getParameter("path");
        byte[] image = getImageByPath(path);
        if(image == null){
            return;
        }
        @Cleanup
        InputStream imageStream = new ByteArrayInputStream(image);
        response.setContentType("image/*");
        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = imageStream.read(buf, 0, 1024)) != -1) {
            out.write(buf, 0, len);
        }
        out.flush();
    }
}
