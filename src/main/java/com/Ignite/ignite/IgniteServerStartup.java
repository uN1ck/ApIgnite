package com.Ignite.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;

public class IgniteServerStartup {
    public static void main(String[] args) {
        run();
    }

    public static Ignite run() {
        return Ignition.start("config/server.xml");
    }
}
