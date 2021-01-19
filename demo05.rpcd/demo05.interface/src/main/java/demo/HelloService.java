package demo;

import org.noear.nami.annotation.NamiClient;

@NamiClient(name = "demo", path = "/demoe/rpc")
public interface HelloService {
    String hello(String name);
}
