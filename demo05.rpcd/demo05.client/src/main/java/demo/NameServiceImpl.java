package demo;

import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.core.handle.MethodType;

//定义远程服务组件（供服务端调用）
@Mapping(value = "/demoe/rpc/name", method = MethodType.SOCKET)
@Component(remoting = true)
public class NameServiceImpl implements NameService {
    @Override
    public String name(String name) {
        return name + "2";
    }
}
