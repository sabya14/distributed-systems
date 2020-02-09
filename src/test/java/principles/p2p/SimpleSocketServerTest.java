package principles.p2p;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class SimpleSocketServerTest {

    @Test
    public void shouldStartToServerAndSendMessageToEachOther() throws IOException {
        SimpleSocketServer s1 = new SimpleSocketServer("127.0.0.1", 8500);
        SimpleSocketServer s2 = new SimpleSocketServer("127.0.0.1", 8501);
        s1.start();
        s2.start();
        s1.sendTCPMessage("Hello S2", "127.0.0.1", 8501);
        s1.sendTCPMessage("Hello S2 1", "127.0.0.1", 8501);
        s1.sendTCPMessage("Hello S2 2", "127.0.0.1", 8501);

    }

}