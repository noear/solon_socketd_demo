package demo;

import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Remoting;
import org.noear.solon.core.handle.MethodType;

//定义远程服务组件
@Mapping(value = "/demoe/rpc", method = MethodType.SOCKET)
@Remoting
public class HelloServiceImpl implements HelloService {
    public String hello(String name) {
        return "name=" + name;
    }
}

