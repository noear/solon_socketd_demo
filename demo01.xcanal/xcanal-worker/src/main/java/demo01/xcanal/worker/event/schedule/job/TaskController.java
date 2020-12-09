package demo01.xcanal.worker.event.schedule.job;

import demo01.xcanal.base.common.BeanLifecycleAtomic;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.Solon;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

@Component
@Slf4j
public class TaskController extends BeanLifecycleAtomic {
    @Inject
    TaskStarter taskStarter;

    @Override
    protected void onStart() throws Exception {
        //demo init
        long jobId = Solon.cfg().getInt(ShellConstants.JOB_ID,1);
        long taskId = Solon.cfg().getInt(ShellConstants.TASK_ID,1);

        //启动应用
        taskStarter.init(jobId,taskId);
        taskStarter.start();
    }

    @Override
    protected void onStop() throws Exception {
        taskStarter.stop();
    }
}
