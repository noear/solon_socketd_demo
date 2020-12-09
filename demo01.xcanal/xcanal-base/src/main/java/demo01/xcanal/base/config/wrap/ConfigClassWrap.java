package demo01.xcanal.base.config.wrap;



import demo01.xcanal.base.config.ConfigDef;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class ConfigClassWrap {
    private static Map<Class<?>, ConfigClassWrap> cached = new ConcurrentHashMap<>();

    /**
     * 根据clz获取一个ClassWrap
     */
    public static ConfigClassWrap get(Class<?> clz) {
        ConfigClassWrap cw = cached.get(clz);
        if (cw == null) {
            cw = new ConfigClassWrap(clz);
            ConfigClassWrap l = cached.putIfAbsent(clz, cw);
            if (l != null) {
                cw = l;
            }
        }
        return cw;
    }

    private final Class<?> _clz;
    private final List<ConfigFieldWrap> fields;
    private final Map<String, Field> fieldMap;


    private ConfigClassWrap(Class<?> clz) {
        _clz = clz;

        //所有字段的包装（自己的 + 父类的）
        fieldMap = new ConcurrentHashMap<>();
        fields = new ArrayList<>();

        doScanAllFields(clz, fieldMap::containsKey, (k, v) -> {
            ConfigDef anno = v.getAnnotation(ConfigDef.class);
            if (anno != null) {
                fieldMap.put(k, v);
                fields.add(new ConfigFieldWrap(v, anno));
            }
        });
    }

    /**
     * 配置类
     * */
    public Class<?> configClz() {
        return _clz;
    }

    /**
     * 配置字段
     * */
    public List<ConfigFieldWrap> configFields(){
        return Collections.unmodifiableList(fields);
    }



    /**
     * 扫描一个类的所有字段（不能与Snack3的复用；它需要排除非序列化字段）
     */
    private static void doScanAllFields(Class<?> clz, Predicate<String> checker, BiConsumer<String, Field> consumer) {
        if (clz == null) {
            return;
        }

        for (Field f : clz.getDeclaredFields()) {
            int mod = f.getModifiers();

            if (!Modifier.isStatic(mod)) {
                f.setAccessible(true);

                if (checker.test(f.getName()) == false) {
                    consumer.accept(f.getName(), f);
                }
            }
        }

        Class<?> sup = clz.getSuperclass();
        if (sup != Object.class) {
            doScanAllFields(sup, checker, consumer);
        }
    }
}
