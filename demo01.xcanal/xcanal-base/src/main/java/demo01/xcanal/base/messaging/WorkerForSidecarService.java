package demo01.xcanal.base.messaging;

import org.noear.nami.annotation.NamiClient;

@NamiClient("demo:rpc/WorkerForSidecarService")
public interface WorkerForSidecarService {
    boolean taskStart(long jobId, long taskId);

    /**
     * 停止任务（Sidecar 将通过Shell启动Worker来停止任务）
     * */
    boolean taskStop(long jobId, long taskId);
}
