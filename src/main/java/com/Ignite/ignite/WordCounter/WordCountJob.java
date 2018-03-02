package com.Ignite.ignite.WordCounter;

import org.apache.ignite.IgniteException;
import org.apache.ignite.compute.ComputeJobAdapter;

import java.util.List;
import java.util.StringTokenizer;

public class WordCountJob extends ComputeJobAdapter {
    private List<String> _part;

    WordCountJob(List<String> part) {
        this._part = part;
    }

    @Override
    public Object execute() throws IgniteException {
        long result = 0;
        //Считаем токенайзером влоб :З
        for (String string : _part) {
            StringTokenizer st = new StringTokenizer(string);
            result += st.countTokens();
        }
        return result;
    }
}
