package demo01.xcanal.base.config.wrap;


import demo01.xcanal.base.config.ConfigDef;

import java.lang.reflect.Field;

public class ConfigFieldWrap {
    public final Field field;
    public final ConfigDef definition;

    public ConfigFieldWrap(Field field, ConfigDef anno) {
        this.field = field;
        this.definition = anno;
    }

    public Object get(Object target) {
        try {
            return field.get(target);
        } catch (Exception e) {
            return null;
        }
    }

    public void set(Object target, Object val) {
        try {
            field.set(target, val);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
