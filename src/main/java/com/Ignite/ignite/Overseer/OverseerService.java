package com.Ignite.ignite.Overseer;

import com.Ignite.ignite.ResultSetter.DummyResultSetter;
import com.Ignite.ignite.ResultSetter.ResultSetter;
import com.Ignite.ignite.SegmentGetter.DummySegmentGetter;
import com.Ignite.ignite.SegmentGetter.SegmentGetter;
import com.Ignite.ignite.WordCounter.WordCountTask;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteAtomicLong;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.lang.IgniteRunnable;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.apache.ignite.services.Service;
import org.apache.ignite.services.ServiceContext;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class OverseerService implements Service, Overseer {
    @IgniteInstanceResource
    private Ignite ignite;
    private IgniteAtomicLong _result;

    //region visitors
    private SegmentGetter _segmentGetter;
    private ResultSetter _resultSetter;
    //endregion

    public OverseerService() {

        _segmentGetter = new DummySegmentGetter();
        _resultSetter = new DummyResultSetter();
    }


    @Override
    public void oversee() {

        List<String> segment = null;
        segment = _segmentGetter.getSegment();
        IgniteCompute compute = ignite.compute();

        while (segment.size() > 0) {
            long cnt = compute.execute(WordCountTask.class, segment);
            _result.addAndGet(cnt);
            segment = _segmentGetter.getSegment();
        }

        _resultSetter.setResult(_result.get());
    }

    @Override
    public void cancel(ServiceContext serviceContext) {
        System.out.println("Service was cancelled: " + serviceContext.name());
    }

    @Override
    public void init(ServiceContext serviceContext) throws Exception {
        _result = ignite.atomicLong("wordsCalculationResult", 0, true);
        System.out.println("Service was initialized: " + serviceContext.name());
    }

    @Override
    public void execute(ServiceContext serviceContext) throws Exception {
        System.out.println("Executing distributed service: " + serviceContext.name());
        //TODO: Execute here! (Может сюда нао подсунуть ВордКоунтДжоб? и тут тоже вырасет шедуллер?
        this.oversee();
//        ExecutorService exec = ignite.executorService();
//        exec.execute(WordCountTask.class,segment);
//
//
//        for (final String word : "Print words using runnable".split(" ")) {
//            // Execute runnable on some node.
//            exec.submit(new IgniteRunnable() {
//                @Override
//                public void run() {
//                    System.out.println(">>> Printing '" + word + "' on this node from grid job.");
//                }
//            });
//        }
    }
}
