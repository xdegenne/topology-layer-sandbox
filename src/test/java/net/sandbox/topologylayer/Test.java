package net.sandbox.topologylayer;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.Iterator;

import org.junit.Assert;

public class Test {

    @org.junit.Test
    public void simple2layers() {

        TopologyComposite layers = new TopologyComposite();

        Topology designTopology = new Topology();
        layers.addLayer(designTopology);

        Node compute1 = new Node("root.Compute");
        designTopology.addNode("compute1", compute1);

        assertIteratorCount(layers.nodeNames(), 1);

        Topology matchedTopology = new Topology();
        Node network1 = new Node("root.Network");
        matchedTopology.addNode("network1", network1);

        layers.addLayer(matchedTopology);

        assertIteratorCount(layers.nodeNames(), 2);
        assertSame(compute1, layers.getNode("compute1"));
        assertSame(network1, layers.getNode("network1"));
    }

    @org.junit.Test
    public void simpleRemove() {

        TopologyComposite layers = new TopologyComposite();

        Topology designTopology = new Topology();
        layers.addLayer(designTopology);

        Node compute1 = new Node("root.Compute");
        designTopology.addNode("compute1", compute1);

        Topology matchedTopology = new Topology();
        layers.addLayer(matchedTopology);

        Node compute2 = new Node("root.Compute");
        matchedTopology.addNode("compute2", compute2);
        
        matchedTopology.maskNode("compute1");
        designTopology.removeNode("compute1");

        assertIteratorCount(layers.nodeNames(), 1);
    }

    @org.junit.Test
    public void testMask2layers() {

        TopologyComposite layers = new TopologyComposite();

        Topology designTopology = new Topology();
        layers.addLayer(designTopology);

        Node compute1 = new Node("root.Compute");
        designTopology.addNode("compute1", compute1);

        Topology matchedTopology = new Topology();
        Node network1 = new Node("root.Network");
        matchedTopology.addNode("network1", network1);

        layers.addLayer(matchedTopology);

        matchedTopology.maskNode("compute1");

        assertIteratorCount(layers.nodeNames(), 1);
        assertNull(layers.getNode("compute1"));
        assertSame(network1, layers.getNode("network1"));

    }

    @org.junit.Test
    public void testSubstitute2layers() {

        TopologyComposite layers = new TopologyComposite();

        Topology designTopology = new Topology();
        layers.addLayer(designTopology);

        Node compute1 = new Node("root.Compute");
        designTopology.addNode("compute1", compute1);

        Topology matchedTopology = new Topology();
        layers.addLayer(matchedTopology);

        Node compute11 = new Node("root.Compute");
        matchedTopology.substituteNode("compute1", compute11);

        assertIteratorCount(layers.nodeNames(), 1);
        assertSame(compute11, layers.getNode("compute1"));

    }

    @org.junit.Test
    public void testAdd3layers() {

        TopologyComposite layers = new TopologyComposite();

        Topology designTopology = new Topology();
        layers.addLayer(designTopology);

        Node compute1 = new Node("root.Compute");
        designTopology.addNode("compute1", compute1);

        Topology substitutedTopology = new Topology();
        layers.addLayer(substitutedTopology);

        Node compute2 = new Node("root.Compute");
        substitutedTopology.addNode("compute2", compute2);

        Topology matchedTopology = new Topology();
        layers.addLayer(matchedTopology);

        Node compute3 = new Node("root.Compute");
        matchedTopology.addNode("compute3", compute3);

        assertIteratorCount(layers.nodeNames(), 3);
        assertSame(compute1, layers.getNode("compute1"));
        assertSame(compute2, layers.getNode("compute2"));
        assertSame(compute3, layers.getNode("compute3"));

    }

    @org.junit.Test
    public void test3layersMask3() {

        TopologyComposite layers = new TopologyComposite();

        Topology designTopology = new Topology();
        layers.addLayer(designTopology);

        Node compute1 = new Node("root.Compute");
        designTopology.addNode("compute1", compute1);

        Topology substitutedTopology = new Topology();
        layers.addLayer(substitutedTopology);

        Topology matchedTopology = new Topology();
        layers.addLayer(matchedTopology);

        matchedTopology.maskNode("compute1");

        assertIteratorCount(layers.nodeNames(), 0);
        assertNull(layers.getNode("compute1"));

    }

    @org.junit.Test
    public void test3layersMask2() {

        TopologyComposite layers = new TopologyComposite();

        Topology designTopology = new Topology();
        layers.addLayer(designTopology);

        Node compute1 = new Node("root.Compute");
        designTopology.addNode("compute1", compute1);

        Topology substitutedTopology = new Topology();
        layers.addLayer(substitutedTopology);
        substitutedTopology.maskNode("compute1");

        Topology matchedTopology = new Topology();
        layers.addLayer(matchedTopology);

        assertIteratorCount(layers.nodeNames(), 0);
        assertNull(layers.getNode("compute1"));

    }

    private static void printTopology(String message, ITopology topology) {
        System.out.println(" -------- " + message + " --------");
        Iterator<String> nodeNames = topology.nodeNames();
        while (nodeNames.hasNext()) {
            System.out.println(nodeNames.next());
        }
    }

    private void assertIteratorCount(Iterator<?> iterator, int excepted) {
        int i = 0;
        for (i = 0; iterator.hasNext(); iterator.next(), i++) {
        }
        Assert.assertEquals(excepted, i);
    }

}

