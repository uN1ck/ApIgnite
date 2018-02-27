package com.Ignite.ignite.WordCounter;

import org.apache.ignite.IgniteAtomicLong;
import org.apache.ignite.lang.IgniteRunnable;

import java.util.List;
import java.util.StringTokenizer;

/**
 * Класс вычисления слов в сегменте и сохранения результата в заданный АтомикЛонг
 */
public class WordCounterService implements IgniteRunnable {

    private List<String> _segment;
    private IgniteAtomicLong _result;

    WordCounterService(List<String> segment, IgniteAtomicLong result) {
        this._segment = segment;
        this._result = result;
    }

    @Override
    public void run() {
        long result = 0;
        //Считаем тоенайзером влоб :З
        for (String string : this._segment) {
            StringTokenizer st = new StringTokenizer(string);
            result += st.countTokens();
        }
        this._result.addAndGet(result);
    }
}
