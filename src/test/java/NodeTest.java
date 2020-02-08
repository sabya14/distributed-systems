import org.junit.jupiter.api.Test;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NodeTest {

    @Test
    void shouldSavePortOfClientInputFromSocket() throws IOException {
        int port = 9000;
        Node node = new Node(port);
        Socket s = new Socket("localhost", port);
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        dout.writeUTF(String.valueOf(port));
        assertEquals(node.getActiveNodes(), singletonList(port));
        dout.flush();
        dout.close();
    }


}