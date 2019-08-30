package com.mohan.project.easytools.pool.thread;

import com.mohan.project.easytools.common.ObjectTools;
import com.mohan.project.easytools.common.StringTools;
import com.mohan.project.easytools.log.LogTools;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * easy线程创建工厂
 * @author mohan
 * @date 2019-08-21 15:28:28
 */
public class EasyThreadFactory implements ThreadFactory {

    private static final AtomicInteger POOLNUMBER = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    private EasyThreadFactory() {
        SecurityManager securityManager = System.getSecurityManager();
        group = ObjectTools.isNotNull(securityManager) ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
        namePrefix = StringTools.appendJoinEmpty("easy-pool-", String.valueOf(POOLNUMBER.getAndIncrement()), "-thread-");
    }

    static EasyThreadFactory newCisThreadFactory() {
        return new EasyThreadFactory();
    }

    @Override
    public Thread newThread(Runnable runnable) {
        String threadName = namePrefix + threadNumber.getAndIncrement();
        if(ObjectTools.isNull(runnable)) {
            LogTools.debug("EasyThreadFactory创建线程时！参数[runnable]为空");
            runnable = () -> {};
        }
        Thread thread = new Thread(group, runnable, threadName,0);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        if (thread.getPriority() != Thread.NORM_PRIORITY) {
            thread.setPriority(Thread.NORM_PRIORITY);
        }
        LogTools.debug("EasyThreadFactory新建线程为{0}", threadName);
        return thread;
    }
}