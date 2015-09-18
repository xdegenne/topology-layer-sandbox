package net.sandbox.topologylayer;

import java.util.Iterator;

public interface ITopology {

    public abstract String addNode(String nodeName, Node node);

    public abstract Iterator<String> nodeNames();

    public abstract Node getNode(String nodeName);

    public abstract boolean removeNode(String nodeName);

    void maskNode(String nodeName);

    void substituteNode(String nodeName, Node node);

    boolean isMasked(String nodeName);

    boolean isSubstituted(String nodeName);

}