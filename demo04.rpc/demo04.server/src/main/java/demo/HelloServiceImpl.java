package demo;

import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.core.handle.MethodType;

//定义远程服务组件
@Mapping(value = "/demoe/rpc", method = MethodType.SOCKET)
@Component(remoting = true)
public class HelloServiceImpl implements HelloService {
    public String hello(String name) {
        return "name=" + name;
    }
}

