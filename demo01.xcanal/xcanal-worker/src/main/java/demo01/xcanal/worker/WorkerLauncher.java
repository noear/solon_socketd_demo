package demo01.xcanal.worker;

import demo01.xcanal.base.messaging.SidecarForWorkerService;
import lombok.extern.slf4j.Slf4j;
import org.noear.nami.annotation.EnableNamiClient;
import org.noear.nami.annotation.NamiClient;
import org.noear.solon.Solon;
import org.noear.solon.annotation.Component;
import org.noear.solon.extend.schedule.IJob;

import java.util.concurrent.CountDownLatch;


@Slf4j
@EnableNamiClient
@Component
public class WorkerLauncher implements IJob {

    public static void main(String[] args) {
        CountDownLatch downLatch = new CountDownLatch(1);

        Solon.start(WorkerLauncher.class, args)
                .onEvent(TaskStopEvent.class, (e) -> {
                    downLatch.countDown();
                    System.exit(0);
                });

        System.out.println("Worker 启动完成...");

        try {
            log.info("[TASK STARTED...MAIN THREAD BEGIN TO WAIT]");
            downLatch.await();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //
    // 以下演示一下rpc，向console登记一下
    //

    @NamiClient(value = "canal:rpc/SidecarForWorkerService", configuration = RpcConfiguration.class)
    SidecarForWorkerService sidecar;

    @Override
    public int getInterval() {
        return 2 * 1000;
    }

    @Override
    public void exec() throws Throwable {
        try {
            sidecar.workerRegister(1, 1);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("链接Sidecar失败");
        }
    }
}