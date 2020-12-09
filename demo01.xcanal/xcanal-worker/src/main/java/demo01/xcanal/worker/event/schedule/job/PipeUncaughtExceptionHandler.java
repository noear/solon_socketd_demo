package demo01.xcanal.worker.event.schedule.job;

/**
 * 管道未知异常处理
 * */
public class PipeUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static PipeUncaughtExceptionHandler instance = new PipeUncaughtExceptionHandler();
    public static PipeUncaughtExceptionHandler getInstance(){
        return instance;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
    }
}
