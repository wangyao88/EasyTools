package com.mohan.project.easytools.pool.thread;

import com.google.common.collect.Queues;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 公用线程池常量类
 * @author mohan
 * @date 2019-08-20 15:55:31
 */
public final class EasyThreadPoolExecutorConstant {

    static final String DEFAULT_PROJECT_NAME = "EasyThreadPoolExecutor";

    static final Integer CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;
    static final Integer MAXIMUM_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 4;
    static final Long KEEP_ALIVE_TIME = 60L;
    static final TimeUnit UNIT = TimeUnit.MINUTES;
    static final BlockingQueue<Runnable> WORK_QUEUE = Queues.newLinkedBlockingQueue(Integer.MAX_VALUE);
    static final ThreadFactory THREAD_FACTORY = EasyThreadFactory.newCisThreadFactory();
    static final RejectedExecutionHandler REJECTED_EXECUTION_HANDLER = new EasyRejectedExecutionHandler();
}