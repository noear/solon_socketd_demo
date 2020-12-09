package demo01.xcanal.sidecar;

import demo01.xcanal.base.messaging.ConsoleForSidecarService;
import org.noear.nami.annotation.EnableNamiClient;
import org.noear.nami.annotation.NamiClient;
import org.noear.solon.Solon;
import org.noear.solon.annotation.Component;
import org.noear.solon.extend.schedule.IJob;

@EnableNamiClient
@Component
public class SidecarLauncher implements IJob {
    public static void main(String[] args) {
        Solon.start(SidecarLauncher.class, args, app -> app.enableSocket(true));

        System.out.println("Sidecar 启动完成...");
    }


    //
    // 以下演示一下rpc，向console登记一下
    //

    @NamiClient(value = "canal:rpc/ConsoleForSidecarService", configuration = RpcConfiguration.class)
    ConsoleForSidecarService console;

    @Override
    public int getInterval() {
        return 1000 * 5;
    }

    @Override
    public void exec() throws Throwable {
        try {
            //定时签到
            console.sidecarRegister("s6");
        } catch (Exception ex) {
            System.err.println("链接Console失败");
        }
    }
}
