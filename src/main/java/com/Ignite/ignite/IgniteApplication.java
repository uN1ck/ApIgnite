package com.Ignite.ignite;

import com.Ignite.ignite.Overseer.OverseerService;
import com.Ignite.ignite.ResultSetter.DummyResultSetter;
import com.Ignite.ignite.ResultSetter.ResultSetter;
import com.Ignite.ignite.SegmentGetter.DummySegmentGetter;
import com.Ignite.ignite.SegmentGetter.SegmentGetter;
import com.Ignite.ignite.SegmentGetter.TestSegmentGetter;
import org.apache.ignite.*;
import org.apache.ignite.cluster.ClusterGroup;

import java.io.IOException;

public class IgniteApplication {

    public static void main(String[] args) throws IOException {
        Ignite ignite = IgniteClientStartup.run();
        ClusterGroup clusterGroup = ignite.cluster();

        IgniteServices svcs = ignite.services(clusterGroup);

        SegmentGetter sg = new TestSegmentGetter();
        ResultSetter rs = new DummyResultSetter();

        svcs.deployClusterSingleton("overseerSingleton", new OverseerService(sg, rs));
    }
}
