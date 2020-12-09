package demo01.xcanal.base.task;

/**
 * 任务类型
 *
 * @author noear 2020/11/06
 * */
public enum  TaskType {
    /**
     * 结构
     * */
    SCHEMA(1,"SCHEMA"),
    /**
     * 数据全量
     * */
    DATA_FULL(2,"DATA_FULL"),
    /**
     * 数据增量
     * */
    DATA_INCREMENT(3,"DATA_INCREMENT"),
    /**
     * 数据检查
     * */
    DATA_CHECK(4,"DATA_CHECK");

    public final int code;
    public final String name;

    TaskType(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
