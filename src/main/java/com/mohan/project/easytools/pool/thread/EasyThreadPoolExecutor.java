package com.mohan.project.easytools.pool.thread;

import com.mohan.project.easytools.common.ObjectTools;
import com.mohan.project.easytools.common.StringTools;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.*;

/**
 * 公用线程池
 * @author mohan
 * @date 2019-08-20 15:55:31
 */
@Setter
public class EasyThreadPoolExecutor {

    private Integer corePoolSize;
    private Integer maximumPoolSize;
    private Long keepAliveTime;
    private TimeUnit unit;
    private BlockingQueue<Runnable> workQueue;
    private ThreadFactory threadFactory;
    private RejectedExecutionHandler rejectedExecutionHandler;
    private ThreadPoolExecutor threadPoolExecutor;

    private static final ThreadPoolExecutor DEFAULT_THREAD_POOL_EXECUTOR = EasyThreadPoolExecutorBuilder.newBuilder().build().threadPoolExecutor;

    private EasyThreadPoolExecutor() {}
    
    private void configureThreadPoolExecutor() {
        threadPoolExecutor =
                new ThreadPoolExecutor(corePoolSize,
                                       maximumPoolSize,
                                       keepAliveTime,
                                       unit,
                                       workQueue,
                                       threadFactory,
                                       rejectedExecutionHandler);
    }

    public void shutdown() {
        threadPoolExecutor.shutdown();
    }

    public List<Runnable> shutdownNow() {
        return threadPoolExecutor.shutdownNow();
    }

    public boolean isShutdown() {
        return threadPoolExecutor.isShutdown();
    }

    public boolean isTerminating() {
        return threadPoolExecutor.isTerminating();
    }

    public boolean isTerminated() {
        return threadPoolExecutor.isTerminated();
    }

    public boolean remove(Runnable task) {
        return threadPoolExecutor.remove(task);
    }

    public static Executor getDefaultExecutor() {
        return DEFAULT_THREAD_POOL_EXECUTOR;
    }

    public String info() {
        StringBuilder info = new StringBuilder(200);
        info.append(EasyThreadPoolExecutor.class.getName())
                .append(StringTools.SPACE)
                .append(getStatus())
                .append(StringTools.SPACE)
                .append("[")
                .append("pool size = ")
                .append(threadPoolExecutor.getPoolSize())
                .append(StringTools.COMMA)
                .append(StringTools.SPACE)
                .append("active count = ")
                .append(threadPoolExecutor.getActiveCount())
                .append(StringTools.COMMA)
                .append(StringTools.SPACE)
                .append("queued tasks = ")
                .append(threadPoolExecutor.getTaskCount())
                .append(StringTools.COMMA)
                .append(StringTools.SPACE)
                .append("completed tasks = ")
                .append(threadPoolExecutor.getCompletedTaskCount())
                .append(StringTools.COMMA)
                .append(StringTools.SPACE)
                .append("keep alive time = ")
                .append(threadPoolExecutor.getKeepAliveTime(TimeUnit.MINUTES))
                .append("(min)")
                .append("]");
        return info.toString();
    }

    private String getStatus() {
        if(threadPoolExecutor.isTerminating()) {
            return "terminating";
        }
        if(threadPoolExecutor.isTerminated()) {
            return "terminated";
        }
        if(threadPoolExecutor.isShutdown()) {
            return "shutDown";
        }
        return "running";
    }
    
    public static class EasyThreadPoolExecutorBuilder {

        private Integer corePoolSize;
        private Integer maximumPoolSize;
        private Long keepAliveTime;
        private TimeUnit unit;
        private BlockingQueue<Runnable> workQueue;
        private ThreadFactory threadFactory;
        private RejectedExecutionHandler rejectedExecutionHandler;

        public static EasyThreadPoolExecutorBuilder newBuilder() {
            return new EasyThreadPoolExecutorBuilder();
        }

        public EasyThreadPoolExecutorBuilder corePoolSize(int corePoolSize) {
            this.corePoolSize = corePoolSize;
            return this;
        }

        public EasyThreadPoolExecutorBuilder maximumPoolSize(int maximumPoolSize) {
            this.maximumPoolSize = maximumPoolSize;
            return this;
        }

        public EasyThreadPoolExecutorBuilder keepAliveTime(long keepAliveTime) {
            this.keepAliveTime = keepAliveTime;
            return this;
        }

        public EasyThreadPoolExecutorBuilder timeUnit(TimeUnit timeUnit) {
            this.unit = timeUnit;
            return this;
        }

        public EasyThreadPoolExecutorBuilder workQueue(BlockingQueue<Runnable> workQueue) {
            this.workQueue = workQueue;
            return this;
        }

        public EasyThreadPoolExecutorBuilder threadFactory(ThreadFactory threadFactory) {
            this.threadFactory = threadFactory;
            return this;
        }

        public EasyThreadPoolExecutorBuilder rejectedExecutionHandler(RejectedExecutionHandler rejectedExecutionHandler) {
            this.rejectedExecutionHandler = rejectedExecutionHandler;
            return this;
        }

        public EasyThreadPoolExecutor build() {
            EasyThreadPoolExecutor easyThreadPoolExecutor = new EasyThreadPoolExecutor();
            easyThreadPoolExecutor.setCorePoolSize(getCorePoolSize());
            easyThreadPoolExecutor.setMaximumPoolSize(getMaximumPoolSize());
            easyThreadPoolExecutor.setKeepAliveTime(getKeepAliveTime());
            easyThreadPoolExecutor.setUnit(getTimeUnit());
            easyThreadPoolExecutor.setWorkQueue(getWorkQueue());
            easyThreadPoolExecutor.setThreadFactory(getThreadFactory());
            easyThreadPoolExecutor.setRejectedExecutionHandler(getRejectedExecutionHandler());
            easyThreadPoolExecutor.configureThreadPoolExecutor();
            return easyThreadPoolExecutor;
        }
        
        private Integer getCorePoolSize() {
            return ObjectTools.isNull(this.corePoolSize) ? EasyThreadPoolExecutorConstant.CORE_POOL_SIZE : this.corePoolSize;
        }

        private Integer getMaximumPoolSize() {
            return ObjectTools.isNull(this.maximumPoolSize) ? EasyThreadPoolExecutorConstant.MAXIMUM_POOL_SIZE : this.maximumPoolSize;
        }

        private Long getKeepAliveTime() {
            return ObjectTools.isNull(this.keepAliveTime) ? EasyThreadPoolExecutorConstant.KEEP_ALIVE_TIME : this.keepAliveTime;
        }

        private TimeUnit getTimeUnit() {
            return ObjectTools.isNull(this.unit) ? EasyThreadPoolExecutorConstant.UNIT : this.unit;
        }

        private BlockingQueue<Runnable> getWorkQueue() {
            return ObjectTools.isNull(this.workQueue) ? EasyThreadPoolExecutorConstant.WORK_QUEUE : this.workQueue;
        }

        private ThreadFactory getThreadFactory() {
            return ObjectTools.isNull(this.threadFactory) ? EasyThreadPoolExecutorConstant.THREAD_FACTORY : this.threadFactory;
        }

        private RejectedExecutionHandler getRejectedExecutionHandler() {
            return ObjectTools.isNull(this.rejectedExecutionHandler) ? EasyThreadPoolExecutorConstant.REJECTED_EXECUTION_HANDLER : this.rejectedExecutionHandler;
        }
    }
}