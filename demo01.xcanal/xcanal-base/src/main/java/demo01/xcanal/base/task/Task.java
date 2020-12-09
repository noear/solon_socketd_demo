package demo01.xcanal.base.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.noear.snack.ONode;
import org.noear.snack.core.Jsonable;

import java.io.Serializable;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Jsonable, Serializable {
    /**
     * 工作Id
     * */
    private long jodId;
    public long getJodId() {
        return jodId;
    }


    /**
     * 任务Id
     * */
    private long taskId;
    public long getTaskId() {
        return taskId;
    }

    /**
     * 任务状态
     * */
    private TaskStatus taskStatus = TaskStatus.INIT;
    public TaskStatus getTaskStatus() {
        return taskStatus;
    }


    @Override
    public ONode toJsonNode() {
        ONode tmp = new ONode();
        tmp.set("jodId", jodId);
        tmp.set("taskId", taskId);

        return tmp;
    }

    @Override
    public void fromJsonNode(ONode node) {
        jodId = node.get("jodId").getLong();
        taskId = node.get("taskId").getLong();
    }
}
