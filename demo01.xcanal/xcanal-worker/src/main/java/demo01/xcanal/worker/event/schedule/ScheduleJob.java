package demo01.xcanal.worker.event.schedule;

import com.clougence.cloudcanal.worker.event.schedule.job.TaskController;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;
import org.noear.solon.extend.schedule.IJob;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class ScheduleJob implements IJob {
    public static AtomicBoolean started = new AtomicBoolean(false);

    @Inject
    TaskController taskController;

    @Override
    public int getInterval() {
        return 1000;
    }

    @Override
    public void exec() throws Throwable {
        System.out.println("ScheduleJob exec ...");

        if(started.get()){
            taskController.start();
        }
    }
}
