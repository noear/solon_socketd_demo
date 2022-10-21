package client;

import org.noear.solon.Solon;
import org.noear.solon.core.message.Session;
import org.noear.solon.socketd.SocketD;

/**
 * @author noear 2022/10/22 created
 */
public class ClientApp {
    public static void main(String[] args) {
        Solon.start(ClientApp.class, args);

        //创建会话
        Session session = SocketD.createSession("ws://localhost:12080/ws/test");

        session.listener((session1, message) -> {
            System.out.println("服务端来信：" + message.bodyAsString());
        });

        //发送消息
        session.send("你好啊！");
        System.out.println("发送：你好啊！");
    }
}
