package net.sandbox.topologylayer;

import java.util.Iterator;

public class TopologyLayer implements ITopology {

    private final ITopology top;
    private final ITopology bottom;

    public TopologyLayer(ITopology top, ITopology bottom) {
        super();
        this.top = top;
        this.bottom = bottom;
    }

    @Override
    public String addNode(String nodeName, Node node) {
        return top.addNode(nodeName, node);
    }

    @Override
    public Iterator<String> nodeNames() {
        Iterator<String> topIterator = top.nodeNames();
        if (bottom == null) {
            return topIterator;
        } else {
            Iterator<String> bottomIterator = bottom.nodeNames();
            return new LayeredIterator(top, topIterator, bottomIterator);
        }
    }

    @Override
    public Node getNode(String nodeName) {
        if (top.isMasked(nodeName)) {
            return null;
        }
        Node node = top.getNode(nodeName);
        if (node == null && bottom != null) {
            return bottom.getNode(nodeName);
        }
        return node;
    }

    @Override
    public boolean removeNode(String nodeName) {
        return top.removeNode(nodeName);
    }

    private static class LayeredIterator implements Iterator<String> {

        private ITopology top;
        private Iterator<String> topIterator;
        private Iterator<String> bottomIterator;
        private String next;

        public LayeredIterator(ITopology top, Iterator<String> topIterator, Iterator<String> bottomIterator) {
            super();
            this.top = top;
            this.topIterator = topIterator;
            this.bottomIterator = bottomIterator;
            setNext();
        }

        private void setNext() {
            next = null;
            if (topIterator.hasNext()) {
                next = topIterator.next();
            } else if (bottomIterator.hasNext()) {
                while (bottomIterator.hasNext() && next == null) {
                    String nextCandidate = bottomIterator.next();
                    if (!top.isMasked(nextCandidate) && !top.isSubstituted(nextCandidate)) {
                        next = nextCandidate;
                    }
                }
            } else {
                next = null;
            }
        }


        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public String next() {
            String result = next;
            setNext();
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    @Override
    public void maskNode(String nodeName) {
        top.maskNode(nodeName);
    }

    @Override
    public void substituteNode(String nodeName, Node node) {
        top.substituteNode(nodeName, node);
    }

    @Override
    public boolean isMasked(String nodeName) {
        return top.isMasked(nodeName);
    }

    @Override
    public boolean isSubstituted(String nodeName) {
        return top.isSubstituted(nodeName);
    }

}
