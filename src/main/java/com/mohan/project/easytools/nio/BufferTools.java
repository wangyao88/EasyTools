package com.mohan.project.easytools.nio;

import com.mohan.project.easytools.log.LogTools;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.util.function.Consumer;

/**
 * java nio buffer 相关工具类
 * @author mohan
 * @date 2019-09-03 15:11:26
 */
public class BufferTools {

    /**
     * 利用nio buffer 处理文件内容
     * @param path 文件路径
     * @param consumer 文件内容处理函数
     * @throws IOException
     */
    public static void processBuffer(String path, Consumer<Character> consumer) throws IOException {
        File file = Paths.get(path).toFile();
        try(RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");) {
            FileChannel fileChannel = randomAccessFile.getChannel();
            //create buffer with capacity of 48 bytes
            ByteBuffer buf = ByteBuffer.allocate(48);
            //read into buffer.
            int bytesRead = fileChannel.read(buf);
            while (bytesRead != -1) {
                //make buffer ready for read
                buf.flip();
                while(buf.hasRemaining()){
                    // read 1 byte at a time
                    consumer.accept((char) buf.get());
                }
                //make buffer ready for writing
                buf.clear();
                bytesRead = fileChannel.read(buf);
            }
        }catch (Exception e) {
            LogTools.error("处理文件失败！", e);
        }
    }
}