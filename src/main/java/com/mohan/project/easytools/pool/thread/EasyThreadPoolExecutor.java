package com.mohan.project.easytools.pool.thread;

import com.google.common.collect.Queues;
import com.mohan.project.easytools.common.StringTools;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 公用线程池
 * @author mohan
 * @date 2019-08-20 15:55:31
 */
public enum EasyThreadPoolExecutor {

    /**
     * EasyThreadPoolExecutor单例
     */
    INSTANCE;

    private static BlockingQueue<Runnable> workQueue = Queues.newLinkedBlockingQueue(100000);
    private static int cpuCore = Runtime.getRuntime().availableProcessors() * 2;
    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR =
            new ThreadPoolExecutor(cpuCore,
                   cpuCore*2,
                      1,
                                   TimeUnit.MINUTES,
                                   workQueue,
                                   EasyThreadFactory.newCisThreadFactory(),
                                   new EasyRejectedExecutionHandler());

    public Executor getExecutor() {
        return THREAD_POOL_EXECUTOR;
    }

    public String info() {
        StringBuilder info = new StringBuilder(200);
        info.append(EasyThreadPoolExecutor.class.getName())
            .append(StringTools.SPACE)
            .append(getStatus())
            .append(StringTools.SPACE)
            .append("[")
            .append("pool size = ")
            .append(THREAD_POOL_EXECUTOR.getPoolSize())
            .append(StringTools.COMMA)
            .append(StringTools.SPACE)
            .append("active count = ")
            .append(THREAD_POOL_EXECUTOR.getActiveCount())
            .append(StringTools.COMMA)
            .append(StringTools.SPACE)
            .append("queued tasks = ")
            .append(THREAD_POOL_EXECUTOR.getTaskCount())
            .append(StringTools.COMMA)
            .append(StringTools.SPACE)
            .append("completed tasks = ")
            .append(THREAD_POOL_EXECUTOR.getCompletedTaskCount())
            .append(StringTools.COMMA)
            .append(StringTools.SPACE)
            .append("keep alive time = ")
            .append(THREAD_POOL_EXECUTOR.getKeepAliveTime(TimeUnit.MINUTES))
            .append("(min)")
            .append("]");
        return info.toString();
    }

    private String getStatus() {
        if(THREAD_POOL_EXECUTOR.isTerminating()) {
            return "terminating";
        }
        if(THREAD_POOL_EXECUTOR.isTerminated()) {
            return "terminated";
        }
        if(THREAD_POOL_EXECUTOR.isShutdown()) {
            return "shutDown";
        }
        return "running";
    }
}