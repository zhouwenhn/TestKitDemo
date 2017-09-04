package activity.main.tastlibrary.task;

/**
 * Created by zhouwen on 2017/9/1.
 */
public interface ITaskMonitor {
    /**
     * 执行前
     * @param t
     * @param r
     */
    void beforeExecute(Thread t, Runnable r);

    /**
     * 执行后
     * @param r
     * @param t
     */
    void afterExecute(Runnable r, Throwable t);

    /**
     * 执行结束
     * @param largestPoolSize
     * @param completedTaskCount
     */
    void terminated(int largestPoolSize, long completedTaskCount);

    /**
     * 是否开启线程监控
     * @return
     */
    boolean isOpenMonitor();

}
