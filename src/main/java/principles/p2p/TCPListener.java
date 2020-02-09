package principles.p2p;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class TCPListener extends Thread {
    private final InetSocketAddress endpoint;
    private ServerSocket serverSocket;
    private final AtomicBoolean running = new AtomicBoolean(true);

    public TCPListener(InetSocketAddress endPoint) {
        this.serverSocket = null;
        this.endpoint = endPoint;
    }

    public void shutDown() throws IOException {
        running.set(false);
        this.serverSocket.close();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(endpoint);
            log.info("Listening on " +  endpoint.getAddress());
            while (running.get()){
                Socket socket = serverSocket.accept();
                SocketIOHandler socketIOHandler = new SocketIOHandler(socket);
                String message = socketIOHandler.readFromSocket();
                log.info("Received Message - " +  message);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
