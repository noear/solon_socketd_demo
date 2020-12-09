package demo01.xcanal.worker.event.schedule.job;

import com.clougence.cloudcanal.base.common.BeanLifecycleAtomic;
import com.clougence.cloudcanal.base.common.Lifecycle;
import com.clougence.cloudcanal.base.messaging.protocol.SidecarForWorkerService;
import com.clougence.cloudcanal.base.task.Task;
import com.clougence.cloudcanal.ds.base.pipe.Pipe;
import com.clougence.cloudcanal.ds.base.pipe.PipeManager;
import com.clougence.cloudcanal.ds.base.source.SourceApi;
import com.clougence.cloudcanal.ds.base.source.SourceConsumerRole;
import com.clougence.cloudcanal.ds.base.source.SourceManager;
import com.clougence.cloudcanal.ds.base.source.SourceProducerRole;
import com.clougence.cloudcanal.worker.RpcConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.noear.nami.annotation.NamiClient;
import org.noear.solon.annotation.Component;
import org.noear.solon.core.event.EventBus;

/**
 * 任务启动器
 *
 * @author noear 2020/11/06
 * */
@Component
@Slf4j
public class TaskStarter extends BeanLifecycleAtomic {


    @NamiClient(value = "canal:rpc/SidecarForWorkerService", configuration = RpcConfiguration.class)
    SidecarForWorkerService sidecar;

    //配置
    private Task task;

    //管道
    private Pipe pipe;

    //生产端
    private SourceApi source;
    private SourceProducerRole sourceProducer;

    //消费端
    private SourceApi target;
    private SourceConsumerRole targetConsumer;


    protected void init(long jobId, long taskId) {
        task = sidecar.taskGet(jobId, taskId);

        source = SourceManager.getInstance().apiGet(task.getTaskConfig().getProducerConfig().getSourceCode());
        target = SourceManager.getInstance().apiGet(task.getTaskConfig().getConsumerConfig().getSourceCode());

        if(source == null || target == null){
            return;
        }

        //创建默认管道
        pipe = PipeManager.getInstance().get().createPipe(task.getTaskConfig(), PipeUncaughtExceptionHandler.getInstance());

        //创建源的生产者角色
        sourceProducer = source.createProducerRole(task.getTaskConfig());

        //创建源的消费者角色
        targetConsumer = target.createConsumerRole(task.getTaskConfig());


    }


    @Override
    protected void onStart() throws Exception {
        if(targetConsumer == null){
            return;
        }

        //启动消费者
        targetConsumer.start();

        //启动管道（+订阅）
        pipe.subscribe(targetConsumer);
        pipe.then(sourceProducer);
        pipe.start();

        //启动生产者（+生产）
        sourceProducer.start();
        sourceProducer.produce(pipe);
    }

    @Override
    protected void onStop() throws Exception {
        if(targetConsumer == null){
            return;
        }

        try {
            //顺序别乱
            sourceProducer.stop();
            pipe.stop();
            targetConsumer.stop();
        } finally {
            EventBus.push(TaskStopEvent.instance);
        }
    }

    @Override
    protected void onUncaughtException(Lifecycle lifecycle, Throwable e) {
        String errMsg = "start task error.task id:" + task.getTaskId() + ",msg:" + ExceptionUtils.getRootCauseMessage(e);
        log.error(errMsg, e);
        System.exit(-1);
    }
}
