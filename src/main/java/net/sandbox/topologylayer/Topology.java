package net.sandbox.topologylayer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Topology implements ITopology {

    private Map<String, Node> nodes = new HashMap<String, Node>();
    private Set<String> maskedNode = new HashSet<String>();
    private Set<String> substitutedNode = new HashSet<String>();
    
    @Override
    public String addNode(String nodeName, Node node) {
        // TODO if name exists append index
        nodes.put(nodeName, node);
        return nodeName;
    }

    @Override
    public Iterator<String> nodeNames() {
        return nodes.keySet().iterator();
    }

    @Override
    public Node getNode(String nodeName) {
        return nodes.get(nodeName);
    }

    @Override
    public boolean removeNode(String nodeName) {
        Node node = nodes.remove(nodeName);
        return node != null;
    }

    @Override
    public void maskNode(String nodeName) {
        if (nodes.containsKey(nodeName)) {
            throw new RuntimeException("Make no sens to mask your own nodes");
        }
        maskedNode.add(nodeName);
    }

    @Override
    public void substituteNode(String nodeName, Node node) {
        substitutedNode.add(nodeName);
        addNode(nodeName, node);
    }

    @Override
    public boolean isMasked(String nodeName) {
        return maskedNode.contains(nodeName);
    }

    @Override
    public boolean isSubstituted(String nodeName) {
        return substitutedNode.contains(nodeName);
    }

}
