package demo01.xcanal.base.task;

/**
 * 工作状态
 * */
public enum JobStatus {
    /**
     * 正常
     * */
    NORMAL("NORMAL"),

    /**
     * 异常的（执行中出错了...）
     * */
    ABNORMAL("ABNORMAL"),

    /**
     * 删除
     * */
    DELETE("DELETE");

    public final String code;
    JobStatus(String code) {
        this.code = code;
    }
}
