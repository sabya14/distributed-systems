import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Node {

    private int port;
    private ServerSocket serverSocket;
    private Socket socket;
    private List<Integer> activeNodes;

    public Node(int port) throws IOException {
        this.port = port;
        this.activeNodes = new ArrayList<>();
        this.startNode();
    }

    private void startNode() throws IOException {
        this.serverSocket = new ServerSocket(this.port);
        this.socket = serverSocket.accept();
        System.out.println("Starting Socket, accepting connection");
        DataInputStream din = new DataInputStream(socket.getInputStream());
        this.listen(din);
    }

    public Socket getSocket() {
        return socket;
    }
    private void listen(DataInputStream din) throws IOException {
//        while (true) {
//            String str=din.readUTF();
//            System.out.println("client says: " + str);
//            activeNodes.add(Integer.valueOf(str));
//        }
    }

    public List<Integer> getActiveNodes() {
        return activeNodes;
    }
}
