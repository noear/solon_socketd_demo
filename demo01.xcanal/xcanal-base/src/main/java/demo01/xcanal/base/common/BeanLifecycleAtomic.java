package demo01.xcanal.base.common;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class BeanLifecycleAtomic implements BeanLifecycle {
    protected final AtomicBoolean started = new AtomicBoolean(false);

    @Override
    public void start() {
        if (started.compareAndSet(false, true)) {
            try {
                onStart();
            } catch (Throwable e) {
                onUncaughtException(Lifecycle.START, e);
            }
        }
    }

    @Override
    public void stop() {
        if (started.compareAndSet(true, false)) {
            try {
                onStop();
            } catch (Throwable e) {
                onUncaughtException(Lifecycle.STOP, e);
            }
        }
    }

    protected abstract void onStart() throws Exception;

    protected abstract void onStop() throws Exception;

    protected void onUncaughtException(Lifecycle lifecycle, Throwable e) {
        e.printStackTrace();
    }
}
