package com.Ignite.ignite;

import com.Ignite.ignite.Overseer.OverseerService;
import com.Ignite.ignite.ResultSetter.DummyResultSetter;
import com.Ignite.ignite.ResultSetter.ResultSetter;
import com.Ignite.ignite.SegmentGetter.DummySegmentGetter;
import com.Ignite.ignite.SegmentGetter.SegmentGetter;
import org.apache.ignite.*;

import java.io.IOException;

public class IgniteApplication {

    public static void main(String[] args) throws IOException {
        Ignite ignite = IgniteClientStartup.run();
        IgniteServices svcs = ignite.services();
        SegmentGetter sg = new DummySegmentGetter(2);
        ResultSetter rs = new DummyResultSetter();

        svcs.deployClusterSingleton("overseerSingleton", new OverseerService(sg, rs));
    }
}
