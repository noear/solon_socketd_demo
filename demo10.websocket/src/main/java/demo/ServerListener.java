package demo;

import org.noear.solon.annotation.ServerEndpoint;
import org.noear.solon.core.message.Listener;
import org.noear.solon.core.message.Message;
import org.noear.solon.core.message.MessageFlag;
import org.noear.solon.core.message.Session;

//定义服务端监听
@ServerEndpoint("/ws/test")
public class ServerListener implements Listener {
    @Override
    public void onOpen(Session session) {
        System.out.println("有客户端链上来喽...");
    }

    @Override
    public void onClose(Session session) {
        System.out.println("有客户端链关了!");
    }

    @Override
    public void onMessage(Session session, Message message) {
        //收到消息，做业务处理
        System.out.println("服务端：我收到：" + message.bodyAsString());

        for(Session s1 : session.getOpenSessions()){
            s1.send("服务端：我收到：" + message.bodyAsString());
        }
    }
}
