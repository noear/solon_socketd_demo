package demo;

import org.noear.solon.Utils;
import org.noear.solon.annotation.ServerEndpoint;
import org.noear.solon.core.message.Listener;
import org.noear.solon.core.message.Message;
import org.noear.solon.core.message.MessageFlag;
import org.noear.solon.core.message.Session;
import org.noear.solon.socketd.util.HeaderUtil;

import java.io.IOException;
import java.util.Map;

//定义服务端监听
@ServerEndpoint
public class ServerListener implements Listener {
    @Override
    public void onOpen(Session session) {
        System.out.println("有客户端链上来喽...");
    }

    @Override
    public void onMessage(Session session, Message message) throws IOException {
        //如果是握手，则做鉴权处理
        if (message.flag() == MessageFlag.handshake) {
            if (Utils.isEmpty(message.header())) {
                session.close();
            } else {
                Map<String, String> headers = HeaderUtil.decodeHeaderMap(message.header());
                if ("1".equals(headers.get("token"))) {
                    //如果token是1，则设为成功握手
                    session.setHandshaked(true);
                } else {
                    session.close();
                }
            }

            return;
        }

        //没有握手成功之前，不能做别的事；且自动断开链接
        if (session.getHandshaked() == false) {
            session.close();
        }

        //如果是心跳，则乎略
        if (message.flag() == MessageFlag.heartbeat) {
            System.out.println("服务端：我收到心跳");
            return;
        }

        //业务处理
        System.out.println("服务端：我收到：" + message.bodyAsString());
    }
}
