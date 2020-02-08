package principles.consistenthashing;

import java.security.DigestException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashing {
    private final SortedMap<Long, Node> ring = new TreeMap<>();
    private MD5Hashing hashing;
    private final int maxServers;

    public ConsistentHashing(List<PhysicalNode> physicalNodes, MD5Hashing hashing, int maxServers) throws NoSuchAlgorithmException {
        this.maxServers = maxServers;
        this.hashing = hashing;
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

    public void addNodes(Node node) throws DigestException {
        addToRing((PhysicalNode) node);
    }

    public void removeNode(Node node) {
        ring.remove(hashing.hash(node.getKey()));
    }

    public String routeTo(String requestObject) {
        long requestHash = hashing.hash(requestObject);
        SortedMap<Long, Node> nodesToLeftOfRequestedHash = this.ring.tailMap(requestHash);
        Long routerKey = !nodesToLeftOfRequestedHash.isEmpty() ? nodesToLeftOfRequestedHash.firstKey() : ring.firstKey();
        return ring.get(routerKey).getKey();
    }
}
