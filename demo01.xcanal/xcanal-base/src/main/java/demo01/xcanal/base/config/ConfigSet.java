package demo01.xcanal.base.config;

import demo01.xcanal.base.common.BeanJsonable;
import demo01.xcanal.base.common.BeanPropable;
import demo01.xcanal.base.config.wrap.ConfigClassWrap;
import demo01.xcanal.base.config.wrap.ConfigFieldWrap;
import demo01.xcanal.base.utils.TypeUtils;
import org.apache.commons.lang3.StringUtils;
import org.noear.snack.ONode;

import java.io.Serializable;
import java.util.*;

/**
 * 配置集合，统一系统内所有的配置（适用：其它参数配置，生产端参数配置，消费端参数配置）
 *
 * 子类的字段要添加transient，避免多余的序列化（序列化交由：items）
 *
 * @author noear 2020/11/06
 * */
public class ConfigSet implements BeanJsonable, BeanPropable, Serializable {

    public ConfigSet(){
        loadByDefinition();
    }

    /**
     * 配置项（配置的持久化唯一载体）
     */
    protected  final Map<String, ConfigItem> items = new LinkedHashMap<String, ConfigItem>();

    /**
     * 添加配置
     */
    protected void putItem(ConfigItem item) {
        items.put(item.getName(), item);
    }

    /**
     * 获取配置
     */
    public ConfigItem getItem(String name) {
        return items.get(name);
    }

    /**
     * 获取所有配置
     */
    public Map<String, ConfigItem> getItems() {
        return Collections.unmodifiableMap(items);
    }

    /**
     * 转为Json
     */
    @Override
    public ONode toJsonNode() {
        //同步一下字段
        syncByDefinition();

        return ONode.loadObj(items.values());
    }

    @Override
    public void fromJsonNode(ONode node) {
        if(node == null){
            return;
        }

        List<ConfigItem> tmp = node.toObjectList(ConfigItem.class);
        for (ConfigItem m1 : tmp) {
            putItem(m1);
        }

        //重新加载字段值
        loadByDefinition();
    }

    /**
     * 转为Properties
     * */
    public Properties toProperties(){
        //同步一下字段
        syncByDefinition();

        Properties tmp = new Properties();
        getItems().forEach((k,v)->{
            tmp.setProperty(k,v.getValue());
        });

        return tmp;
    }


    public void fromProperties(Properties props) {
        props.forEach((k, v) -> {
            if (k instanceof String && v instanceof String) {
                putItem(new ConfigItem((String) k, (String) v));
            }
        });

        //重新加载字段值
        loadByDefinition();
    }

    ///////////////////////////////////////////////////////

    /**
     * 复制配制
     */
    public <T extends ConfigSet> T copyBy(ConfigSet configSet) {
        items.putAll(configSet.items);

        //初始化定义，绑定字段值
        loadByDefinition();

        return (T) this;
    }


    /**
     * 根据定义同步
     * */
    private void syncByDefinition() {
        //
        // 使用缓存机制
        //
        List<ConfigFieldWrap> fields = ConfigClassWrap.get(this.getClass()).configFields();

        for (ConfigFieldWrap f1 : fields) {
            ConfigItem cfg1 = items.get(f1.definition.name());
            Object obj1 = f1.get(this);

            if (obj1 == null || cfg1 == null) {
                continue;
            }

            cfg1.setValue(obj1.toString());
        }
    }


    /**
     * 根据定义加载
     */
    private void loadByDefinition() {
        //
        // 使用缓存机制
        //
        List<ConfigFieldWrap> fields = ConfigClassWrap.get(this.getClass()).configFields();

        for (ConfigFieldWrap f1 : fields) {
            ConfigItem cfg1 = items.get(f1.definition.name());

            if (cfg1 == null) {
                cfg1 = new ConfigItem();

                //绑定定义
                cfg1.bindDef(f1.definition);

                cfg1.setValue(f1.definition.valueDef());

                putItem(cfg1);
            } else {
                //重新绑定定义（以免之前是手动加的）
                cfg1.bindDef(f1.definition);

                if (StringUtils.isEmpty(cfg1.getValue())) {
                    cfg1.setValue(f1.definition.valueDef());
                }
            }

            Object val2 = TypeUtils.change(cfg1.getValue(), f1.field.getType());

            if (val2 != null) {
                //将值设到字段上
                f1.set(this, val2);
            }
        }
    }
}
