package demo01.xcanal.base.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 配置项
 *
 * @author noear 2020/11/06
 * */
@Setter
@Getter
public class ConfigItem implements Serializable {

    /**
     * 配置名称
     */
    private String name;

    /**
     * 标签
     * */
    private String label;

    /**
     * 值（运行中的值）
     */
    private String value;

    /**
     * 新的值
     */
    private String valueNew;

    /**
     * 默认值
     */
    private String valueDef;

    /**
     * 修改后的值
     */
    private String valueAft;

    /**
     * 值的范围
     * */
    private String valueScope;


    /**
     * 描述
     * */
    private String description;

    /**
     * 描述国际化
     * */
    private String descriptionI18n;

    /**
     * 是否重启
     */
    private Boolean isRestart;

    /**
     * 是否只读
     */
    private Boolean isReadonly;

    /**
     * 是否可见
     * */
    private Boolean isVisible;

    public ConfigItem(){

    }

    public ConfigItem(String name, String value){
        this.name = name;
        this.value = value;
    }

    /**
     * 绑定定义
     * */
    public ConfigItem bindDef(ConfigDef def) {
        name = def.name();
        label = def.label().name;

        valueDef = def.valueDef();
        valueScope = def.valueScope();

        description = def.description();
        descriptionI18n = def.descriptionI18n();

        isRestart = def.isRestart();
        isVisible = def.isVisible();
        isReadonly = def.isReadonly();

        return this;
    }

    //
    // 类型转换
    //

    @Override
    public String toString() {
        return value;
    }

    public int toInt() {
        if (StringUtils.isEmpty(value)) {
            return 0;
        } else {
            return Integer.parseInt(value);
        }
    }

    public long toLong() {
        if (StringUtils.isEmpty(value)) {
            return 0L;
        } else {
            return Long.parseLong(value);
        }
    }

    public double toDouble() {
        if (StringUtils.isEmpty(value)) {
            return 0D;
        } else {
            return Double.parseDouble(value);
        }
    }

    public boolean toBoolean() {
        if (StringUtils.isEmpty(value)) {
            return false;
        } else {
            return Boolean.parseBoolean(value);
        }
    }
}
