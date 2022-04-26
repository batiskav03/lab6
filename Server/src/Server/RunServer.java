package Server;

import java.io.IOException;

public class RunServer {

    public static void main(String[] args) throws IOException{
        new ServerMainThread().start();
    }
}
