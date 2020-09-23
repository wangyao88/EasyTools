package com.mohan.project.easytools.file;

import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.function.Consumer;

/**
 * @author WangYao
 * @since 2020-09-23 10:32
 */
public class MappedByteBufferReadLineTools {

    public static final String DEFAULT_CHAR_SET = StandardCharsets.UTF_8.name();

    public static final Consumer<String> DEFAULT_CONSUMER = System.out::println;

    private static class Index {

        private int limit;
        private int currentReadPosition;

        public Index(int limit, int currentReadPosition) {
            this.limit = limit;
            this.currentReadPosition = currentReadPosition;
        }
    }

    public static void batchReadLine(String path) throws Exception {
        batchReadLine(path, DEFAULT_CONSUMER);
    }

    public static void batchReadLine(String path, Consumer<String> consumer) throws Exception {
        if(consumer == null) {
            consumer = DEFAULT_CONSUMER;
        }
        RandomAccessFile file = null;
        FileChannel fileChannel = null;
        MappedByteBuffer mappedByteBuffer = null;
        try {
            file = new RandomAccessFile(path, "r");
            fileChannel = file.getChannel();
            mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0L, fileChannel.size());
            int limit = mappedByteBuffer.limit();
            Index index = new Index(limit, 0);
            String line = null;
            while ((line = readLine(index, mappedByteBuffer)) != null) {
                consumer.accept(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(mappedByteBuffer != null) {
                cleanMappedByteBuffer(mappedByteBuffer);
            }
            if(fileChannel != null) {
                fileChannel.close();
            }
            if(file != null) {
                file.close();
            }
        }
    }

    /**
     * 指定每行的容量，最大字节数
     * 如果存在行超过指定最大字节，则会
     *
     * @return
     * @throws Exception
     */
    private static String readLine(Index index, MappedByteBuffer mappedByteBuffer) throws Exception {
        int limit = index.limit;
        int currentReadPosition = index.currentReadPosition;
        if (currentReadPosition >= index.limit) {
            return null;
        }
        ByteBuffer bb = ByteBuffer.allocate(1024);
        while (currentReadPosition < limit) {
            byte b = mappedByteBuffer.get();
            currentReadPosition++;
            if (System.getProperty("line.separator").equals("\r\n") && b == 13) {
                mappedByteBuffer.get();
                currentReadPosition++;
                break;
            } else if (b == 10 || b == 13) {
                break;
            } else {
                bb.put(b);
            }
        }
        index.currentReadPosition = currentReadPosition;
        return rightTrim(new String(bb.array(), DEFAULT_CHAR_SET));
    }

    private static String rightTrim(String s) {
        char[] cs = s.toCharArray();
        int pos = 0;
        for (int i = cs.length - 1; i >= 0; i--) {
            String tostr = String.valueOf(cs[i]);
            if (tostr.trim().length() != 0) {
                pos = i;
                break;
            }
        }
        return s.substring(0, pos + 1);
    }

    /**
     * 清理ByteBuffer
     *
     * @throws Exception
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void cleanMappedByteBuffer(MappedByteBuffer mappedByteBuffer) throws Exception {
        AccessController.doPrivileged(new PrivilegedAction() {
            @Override
            public Object run() {
                try {
                    Method getCleanerMethod = mappedByteBuffer.getClass().getMethod("cleaner", new Class[0]);
                    getCleanerMethod.setAccessible(true);
                    sun.misc.Cleaner cleaner = (sun.misc.Cleaner) getCleanerMethod.invoke(mappedByteBuffer, new Object[0]);
                    cleaner.clean();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }
}