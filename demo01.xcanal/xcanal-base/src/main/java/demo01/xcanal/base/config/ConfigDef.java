package demo01.xcanal.base.config;


import java.lang.annotation.*;

/**
 * 配置定义
 * */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConfigDef {
    /**
     * 配置名称
     * */
    String name();

    /**
     * 默认值
     * */
    String valueDef() default "";

    /**
     * 值范围
     * */
    String valueScope() default "";

    /**
     * 标签
     * */
    ConfigLabel label() default ConfigLabel.NORMAL;

    /**
     * 描述
     * */
    String description() default "";


    /**
     * 描述
     * */
    String descriptionI18n() default "";

    /**
     * 是否重启（值变更后）
     * */
    boolean isRestart() default false;

    /**
     * 是否只读
     * */
    boolean isReadonly() default false;

    /**
     * 是否可见
     * */
    boolean isVisible() default true;
}
