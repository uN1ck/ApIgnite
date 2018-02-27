package com.Ignite.ignite;

import org.apache.ignite.Ignite;

import java.util.concurrent.ExecutorService;

public class Executor {

    public static void run(Ignite ignite) {
        ExecutorService exec = ignite.executorService();


    }
}
