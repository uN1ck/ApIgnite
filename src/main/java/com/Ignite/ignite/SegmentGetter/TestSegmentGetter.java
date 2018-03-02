package com.Ignite.ignite.SegmentGetter;

import java.util.Arrays;
import java.util.List;

public class TestSegmentGetter implements SegmentGetter {
    private int _index = 0;

    @Override
    public List<String> getSegment() {
        List<String> result;
        if (_index == 0) {
            result = Arrays.asList("Очень эквивалентный сегмент", "И очень, зараза, убедительный");
        } else {
            result = Arrays.asList("ДА ВООБЩЕ!", "Ни.Разу. НЕ. СОМЕНВАЮСЬ!");
        }
        _index = (_index + 1) % 2;
        return result;
    }
}
