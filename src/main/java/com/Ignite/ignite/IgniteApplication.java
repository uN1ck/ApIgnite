package com.Ignite.ignite;

import com.Ignite.ignite.Overseer.OverseerService;
import org.apache.ignite.*;

import java.io.IOException;

public class IgniteApplication {

    public static void main(String[] args) throws IOException {
        Ignite ignite = IgniteClientStartup.run();
        IgniteServices svcs = ignite.services();
        svcs.deployClusterSingleton("overseerSingleton", new OverseerService());
    }


}
