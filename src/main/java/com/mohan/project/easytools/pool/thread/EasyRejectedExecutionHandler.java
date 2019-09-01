package com.mohan.project.easytools.pool.thread;

import com.mohan.project.easytools.common.StringTools;
import com.mohan.project.easytools.log.LogTools;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池拒绝策略
 * 由调用线程执行
 * @author mohan
 * @date 2019-08-20 16:50:50
 */
public class EasyRejectedExecutionHandler implements RejectedExecutionHandler {

    EasyRejectedExecutionHandler() {}

    @Override
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
        String warnInfo =
                StringTools.appendJoinEmpty(EasyThreadPoolExecutorConstant.DEFAULT_PROJECT_NAME,
                                            "线程池已阻塞，缓冲队列已满， 执行拒绝策略",
                                            EasyRejectedExecutionHandler.class.getName(),
                                            "，由主线程执行当前任务[",
                                            runnable.toString(),
                                            "]。线程池当前状况：\n",
                                            executor.toString());
        LogTools.warn(warnInfo);
        if (!executor.isShutdown()) {
            runnable.run();
        }
    }
}