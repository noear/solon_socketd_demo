package demo;

import org.noear.nami.annotation.NamiClient;
import org.noear.solon.socketd.annotation.Handshake;

import java.io.IOException;

@NamiClient("demo:/demoe/rpc")
public interface HelloService {
    @Handshake
    boolean auth(String sn, String token);

    String hello(String name);
}
