package demo;

import org.noear.solon.Solon;
import org.noear.solon.core.message.Session;
import org.noear.solon.socketd.SocketD;

//启动客户端
public class ClientApp {
    public static void main(String[] args) throws Throwable {
        //启动Solon容器（SocketD bean&plugin 由solon容器管理）
        Solon.start(ClientApp.class, args);
    }
}
