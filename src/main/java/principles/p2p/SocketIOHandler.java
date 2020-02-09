package principles.p2p;

import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

@Slf4j
public class SocketIOHandler {
    Socket clientSocket;

    public SocketIOHandler(Socket clientSocket) throws SocketException {
        this.clientSocket = clientSocket;
        clientSocket.setSoTimeout(5000);
    }

    public String readFromSocket() throws IOException {
        DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
        return dis.readUTF();
    }

    public void writeToSocket(String messsage) throws IOException {
        DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
        dos.writeUTF(messsage);
    }
}
