package activity.main.tastlibrary.task;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/**
 * Created by zhouwen on 2017/9/1.
 */

public class MonitorsThreadPoolExecutor extends ThreadPoolExecutor {

    private static Logger LOGGER    = Logger.getLogger("MonitorsThreadPoolExecutor");

    private final ThreadLocal<Long> mStartTime = new ThreadLocal<>();
    private final AtomicLong mNumTasks = new AtomicLong();
    private final AtomicLong mTotalTime = new AtomicLong();
    private ConcurrentHashMap<String, ITaskMonitor> mTaskMonitors =
            new ConcurrentHashMap<>();



    public MonitorsThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public MonitorsThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public MonitorsThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public MonitorsThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    /**
     * @see ThreadPoolExecutor beforeExecute()
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        for (ITaskMonitor taskMonitor : mTaskMonitors.values()) {
            if (taskMonitor.isOpenMonitor()) {
                taskMonitor.beforeExecute(t, r);
            }
        }
        LOGGER.severe("Thread#beforeExecute>>>"+String.format("Thread %s: start %s", t, r));
        mStartTime.set(System.nanoTime());
    }

    /**
     * @see ThreadPoolExecutor afterExecute()
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        for (ITaskMonitor taskMonitor : mTaskMonitors.values()) {
            if (taskMonitor.isOpenMonitor()) {
                taskMonitor.afterExecute(r, t);
            }
        }
        long endTime = System.nanoTime(); //单位 ns
        long taskTime = endTime - mStartTime.get();//1秒=1,000,000,000 纳秒(ns)  1ms = 1,000,000 纳秒(ns)  time=2 982 488ns
        mNumTasks.incrementAndGet();
        mTotalTime.addAndGet(taskTime);
        LOGGER.severe("Thread#afterExecute>>>"+String.format("Thread %s: end %s, time=%d ms", t, r, taskTime/1000000));
    }

    /**
     * @see ThreadPoolExecutor terminated()
     */
    @Override
    protected void terminated() {
        super.terminated();
        for (ITaskMonitor taskMonitor : mTaskMonitors.values()) {
            if (taskMonitor.isOpenMonitor()) {
                taskMonitor.terminated(getLargestPoolSize(), getCompletedTaskCount());
            }
        }
        LOGGER.severe("Thread#terminated>>>"+String.format("Terminated: avg time=%dns", mTotalTime.get() / mNumTasks.get()));

    }

    public ITaskMonitor addMonitorTask(String key, ITaskMonitor task, boolean onlyIfAbsent) {
        if (onlyIfAbsent) {
            synchronized (this) {
                return mTaskMonitors.put(key, task);
            }
        } else {
            synchronized (this) {
                return mTaskMonitors.putIfAbsent(key, task);
            }
        }
    }

    public ITaskMonitor addMonitorTask(String key, ITaskMonitor task) {
        return addMonitorTask(key, task, true);
    }

    public ITaskMonitor removeMonitorTask(ITaskMonitor task) {
        synchronized (this) {
            return mTaskMonitors.remove(task);
        }
    }

}
