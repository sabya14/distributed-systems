package principles.consistenthashing;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsitentHashingTest {

    @Test
    void shouldAddPhysicalNodesToHashRingDuringSetup() {
        PhysicalNode p1 = new PhysicalNode("127.0.0.1", "80");
        PhysicalNode p2 = new PhysicalNode("127.0.0.2", "80");
        PhysicalNode p3 = new PhysicalNode("127.0.0.3", "80");
        PhysicalNode p4 = new PhysicalNode("127.0.0.4", "80");
        List<PhysicalNode> physicalNodes = List.of(p1, p2,p3, p4);
        ConsitentHashing consitentHashing = new ConsitentHashing(physicalNodes, 100);
        assertEquals(consitentHashing.getAllNodes().size(), 4);
    }
}