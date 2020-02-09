package principles.p2p;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;



@Slf4j
public class SimpleSocketServer  {
    private int port;
    private String host;
    private TCPListener tcpListener;

    public SimpleSocketServer(String host, int port) {
        this.port = port;
        this.host = host;
        this.tcpListener = new TCPListener(new InetSocketAddress(host, port));
    }

    public void start(){
        tcpListener.start();
    }

    public void sendTCPMessage(String message, String host, int port) throws IOException {
        SocketIOHandler socketIOHandler = new SocketIOHandler(new Socket(host, port));
        log.info("Sending message: " + message);
        socketIOHandler.writeToSocket(message);
    }

    public void stop() throws IOException {
        tcpListener.shutDown();
    }
}
