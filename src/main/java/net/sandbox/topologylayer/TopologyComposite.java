package net.sandbox.topologylayer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class TopologyComposite implements ITopology {

    private Deque<ITopology> layers = new ArrayDeque<ITopology>();

    public void addLayer(ITopology topology) {
        ITopology last = null;
        if (!layers.isEmpty()) {
            last = layers.getLast();
        }

        TopologyLayer link = new TopologyLayer(topology, last);
        layers.add(link);
    }

    @Override
    public String addNode(String nodeName, Node node) {
        if (layers.isEmpty()) {
            throw new RuntimeException("Empty composite");
        }
        return layers.getLast().addNode(nodeName, node);
    }

    @Override
    public Iterator<String> nodeNames() {
        if (layers.isEmpty()) {
            throw new RuntimeException("Empty composite");
        }
        return layers.getLast().nodeNames();
    }

    @Override
    public Node getNode(String nodeName) {
        if (layers.isEmpty()) {
            throw new RuntimeException("Empty composite");
        }
        return layers.getLast().getNode(nodeName);
    }

    @Override
    public boolean removeNode(String nodeName) {
        if (layers.isEmpty()) {
            throw new RuntimeException("Empty composite");
        }
        return layers.getLast().removeNode(nodeName);
    }

    @Override
    public void maskNode(String nodeName) {
        if (layers.isEmpty()) {
            throw new RuntimeException("Empty composite");
        }
        layers.getLast().maskNode(nodeName);
    }

    @Override
    public void substituteNode(String nodeName, Node node) {
        if (layers.isEmpty()) {
            throw new RuntimeException("Empty composite");
        }
        layers.getLast().substituteNode(nodeName, node);
    }

    @Override
    public boolean isMasked(String nodeName) {
        if (layers.isEmpty()) {
            throw new RuntimeException("Empty composite");
        }
        return layers.getLast().isMasked(nodeName);
    }

    @Override
    public boolean isSubstituted(String nodeName) {
        if (layers.isEmpty()) {
            throw new RuntimeException("Empty composite");
        }
        return layers.getLast().isSubstituted(nodeName);
    }

}
