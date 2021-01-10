package demo;

import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.MethodType;
import org.noear.solon.core.message.Session;

//定义远程服务组件
@Mapping(value = "/demoe/rpc", method = MethodType.SOCKET)
@Component(remoting = true)
public class HelloServiceImpl implements HelloService {
    @Override
    public boolean auth(String sn, String token) {
        if ("1".equals(token)) {
            Session session = (Session) Context.current().request();
            session.setHandshaked(true);
            System.out.println("签权成功!");
            return true;
        }else{
            return false;
        }
    }

    public String hello(String name) {
        return "name=" + name;
    }
}

