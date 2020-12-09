package demo01.xcanal.base.messaging;

import demo01.xcanal.base.task.Task;
import org.noear.nami.annotation.NamiClient;
import org.noear.solon.extend.socketd.annotation.Handshake;

@NamiClient("canal:rpc/SidecarForWorkerService")
public interface SidecarForWorkerService {
    /**
     * 工作机注册（此方法为握手方法[即签权]）
     */
    @Handshake
    boolean workerRegister(long jobId, long taskId);

    /**
     * 任务获取
     */
    Task taskGet(long jobId, long taskId);

}
