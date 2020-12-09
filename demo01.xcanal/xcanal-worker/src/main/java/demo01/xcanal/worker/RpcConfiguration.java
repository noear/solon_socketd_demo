package demo01.xcanal.worker;

import org.noear.nami.Nami;
import org.noear.nami.NamiConfiguration;
import org.noear.nami.annotation.NamiClient;
import org.noear.nami.channel.socketd.SocketChannel;
import org.noear.nami.decoder.SnackDecoder;
import org.noear.nami.encoder.SnackTypeEncoder;
import org.noear.solon.core.message.Session;
import org.noear.solon.extend.socketd.SocketD;

public class RpcConfiguration implements NamiConfiguration {
    SocketChannel channel;
    public RpcConfiguration() {
        Session session = SocketD.createSession("tcp://localhost:7701");

        //启用自动心跳
        session.sendHeartbeatAuto(30);

        channel = new SocketChannel(() -> session);
    }

    @Override
    public void config(NamiClient client, Nami.Builder builder) {
        builder.upstream(() -> "tcp://localhost");
        builder.encoder(SnackTypeEncoder.instance);
        builder.decoder(SnackDecoder.instance);
        builder.channel(channel);
    }
}
