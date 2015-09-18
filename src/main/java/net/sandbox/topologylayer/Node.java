package net.sandbox.topologylayer;

import java.util.HashMap;
import java.util.Map;

public class Node {

    private String type;

    public Node(String type) {
        super();
        this.type = type;
    }

    private Map<String, String> property = new HashMap<String, String>();

    @Override
    public String toString() {
        return "Node [type=" + type + ", property=" + property + "]";
    }

}
