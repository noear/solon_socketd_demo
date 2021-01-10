package demo;

import org.noear.solon.annotation.ServerEndpoint;
import org.noear.solon.core.message.Listener;
import org.noear.solon.core.message.Message;
import org.noear.solon.core.message.MessageFlag;
import org.noear.solon.core.message.Session;

import java.io.IOException;

//定义服务端监听
@ServerEndpoint
public class ServerListener implements Listener {
    @Override
    public void onMessage(Session session, Message message) throws IOException {
        if (message.flag() != MessageFlag.handshake) {
            //没有握手成功之前，不能做别的事；且自动断开链接
            if (session.getHandshaked() == false) {
                System.out.println("这个客户端很坏，没签权就想发包:(");
                session.close();
            }
        }
    }
}
