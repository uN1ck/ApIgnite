package com.Ignite.ignite.WordCounter;

import org.apache.ignite.IgniteException;
import org.apache.ignite.compute.ComputeJob;
import org.apache.ignite.compute.ComputeJobResult;
import org.apache.ignite.compute.ComputeTaskSplitAdapter;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WordCountTask extends ComputeTaskSplitAdapter<List<String>, Long> {
    @Override
    protected Collection<? extends ComputeJob> split(int gridSize, List<String> segment) throws IgniteException {
        //TODO: Переписать на разбиение данных из конфига, отсюда сразу вырастет и шедуллер
        List<List<String>> parts = new ArrayList<>();
        int partSize = segment.size() / gridSize;

        for (int i = 0; i < gridSize - 1; i++) {
            parts.add(segment.subList(i * partSize, (i + 1) * partSize));
        }

        List<ComputeJob> jobs = new ArrayList<>(parts.size());
        for (final List<String> part : parts) {
            jobs.add(new WordCountJob(part));
        }

        return jobs;
    }

    @Nullable
    @Override
    public Long reduce(List<ComputeJobResult> results) throws IgniteException {
        long sum = 0;

        for (ComputeJobResult res : results)
            sum += res.<Long>getData();

        return sum;
    }
}
