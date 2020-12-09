package demo01.xcanal.base.task;

/**
 * 任务状态
 * */
public enum  TaskStatus {
    /**
     * 初始化
     * */
    INIT("INIT"),

    /**
     * 运行中
     * */
    RUNNING("RUNNING"),

    /**
     * 异常的
     * */
    ABNORMAL("ABNORMAL"),

    /**
     * 停止
     * */
    STOP("STOP"),

    /**
     * 完成
     * */
    COMPLETE("COMPLETE"),

    /**
     * 等待启动
     * */
    WAIT_START("WAIT_START"),

    /**
     * 等待重启（和等待启动差不多）
     * */
    WAIT_RESTART("WAIT_RESTART"),

    /**
     * 等待完成
     * */
    WAIT_COMPLETE("WAIT_COMPLETE"),

    /**
     * 等待停止
     * */
    WAIT_STOP("WAIT_STOP");

    public final String code;

    TaskStatus(String code) {
        this.code = code;
    }
}
