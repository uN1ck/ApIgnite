package com.Ignite.ignite.Overwatcher;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteAtomicLong;
import org.apache.ignite.lang.IgniteRunnable;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.apache.ignite.services.Service;
import org.apache.ignite.services.ServiceContext;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class OverwatcherService implements Service, Overwatcher {
    @IgniteInstanceResource
    private Ignite ignite;
    private IgniteAtomicLong _result;
    private String segmentName;
    private SegmentGetter _segmentGetter;


    @Override
    public void overwatch() {

        List<String> segment = _segmentGetter.getSegment();
    }

    @Override
    public void cancel(ServiceContext serviceContext) {
        System.out.println("Service was cancelled: " + serviceContext.name());
    }

    @Override
    public void init(ServiceContext serviceContext) throws Exception {
        this._result = ignite.atomicLong(
                serviceContext.name(),  //"wordsCalculationResult",
                0,
                true
        );
        System.out.println("Service was initialized: " + serviceContext.name());
    }

    @Override
    public void execute(ServiceContext serviceContext) throws Exception {
        System.out.println("Executing distributed service: " + serviceContext.name());

        ExecutorService exec = ignite.executorService();

        for (final String word : "Print words using runnable".split(" ")) {
            // Execute runnable on some node.
            exec.submit(new IgniteRunnable() {
                @Override
                public void run() {
                    System.out.println(">>> Printing '" + word + "' on this node from grid job.");
                }
            });
        }
    }
}
