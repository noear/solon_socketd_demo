package demo;

import org.noear.solon.Solon;

/**
 * @author noear 2021/1/19 created
 */
public class ServerApp {
    public static void main(String[] args) {
        Solon.start(ServerApp.class, args, app -> app.enableWebSocket(true));
    }
}
