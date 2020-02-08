package principles.consistenthashing;

import java.security.DigestException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsitentHashing {
    private final SortedMap<Long, Node> ring = new TreeMap<>();
    private MD5Hashing hashing;
    private final int maxServers;

    public ConsitentHashing(List<PhysicalNode> physicalNodes, int maxServers) {
        this.maxServers = maxServers;
        this.hashing = new MD5Hashing(this.maxServers);
        physicalNodes.forEach(physicalNode -> {
            try {
                addToRing(physicalNode);
            } catch (DigestException e) {
                e.printStackTrace();
            }
        });
    }


    private void addToRing(PhysicalNode physicalNode) throws DigestException {
        ring.put(hashing.hash(physicalNode.getKey()), physicalNode);
    }

    public List<Node> getAllNodes() {
        return new ArrayList<Node> (ring.values());
    }
}
