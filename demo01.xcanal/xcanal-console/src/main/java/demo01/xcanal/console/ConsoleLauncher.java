package demo01.xcanal.console;

import org.noear.solon.Solon;

public class ConsoleLauncher {
    public static void main(String[] args) {
        Solon.start(ConsoleLauncher.class, args, app -> app.enableSocket(true));
    }
}
