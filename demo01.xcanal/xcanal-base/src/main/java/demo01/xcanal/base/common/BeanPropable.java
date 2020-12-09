package demo01.xcanal.base.common;

import java.util.Properties;

/**
 * Bean Propable 接口
 *
 * @author noear 2020/11/11
 * */
public interface BeanPropable {
    Properties toProperties();
    void fromProperties(Properties props);
}
