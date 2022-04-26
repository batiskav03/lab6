package Server;

import java.io.IOException;
import java.net.ServerSocket;

public class StaticServerSocket {
    private static ServerSocket server;

    static {
        try {
            server = new ServerSocket(1337);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StaticServerSocket() throws IOException {
    }

    public static ServerSocket getServerSocket() {
        return server;
    }
}
