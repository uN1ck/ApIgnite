package com.Ignite.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCluster;
import org.apache.ignite.Ignition;

public class ClusterServer {
    public Ignite ignite;
    public IgniteCluster cluster;

    public ClusterServer() {
        ignite = Ignition.ignite("examples/config/example-cache.xml");
        cluster = ignite.cluster();
    }
}
