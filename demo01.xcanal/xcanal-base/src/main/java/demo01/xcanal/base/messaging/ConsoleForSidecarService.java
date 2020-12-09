package demo01.xcanal.base.messaging;


import demo01.xcanal.base.task.Task;
import demo01.xcanal.base.task.TaskStatus;
import org.noear.nami.annotation.NamiClient;
import org.noear.solon.extend.socketd.annotation.Handshake;

/**
 * 控制台服务
 * */
@NamiClient("demo:rpc/ConsoleForSidecarService")
public interface ConsoleForSidecarService {

    /**
     * Sidecar 登录（此方法为握手方法[即签权]）
     * */
    @Handshake
    boolean sidecarRegister(String sidecarSn);

    /**
     * 任务获取
     */
    Task taskGet(long jobId, long taskId);

    /**
     * 任务状态获取
     */
    TaskStatus taskStatusGet(long jobId, long taskId);

    /**
     * 任务状态上报
     */
    boolean taskStatusReport(long jobId, long taskId, TaskStatus status);
}

