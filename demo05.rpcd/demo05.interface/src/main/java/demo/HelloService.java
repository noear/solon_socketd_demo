package demo;

import org.noear.nami.annotation.NamiClient;

@NamiClient("demo:/demoe/rpc")
public interface HelloService {
    String hello(String name);
}
