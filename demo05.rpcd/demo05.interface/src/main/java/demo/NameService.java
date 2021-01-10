package demo;

import org.noear.nami.annotation.NamiClient;

@NamiClient("demo:/demoe/rpc/name")
public interface NameService {
    String name(String name);
}
