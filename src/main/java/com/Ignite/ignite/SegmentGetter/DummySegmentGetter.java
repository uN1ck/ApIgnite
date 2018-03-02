package com.Ignite.ignite.SegmentGetter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class DummySegmentGetter implements SegmentGetter {

    private int _segmentLinesCount;
    BufferedReader _br;

    public DummySegmentGetter(int segmentLinesCount) {
        _segmentLinesCount = segmentLinesCount;
        String _path = System.getProperty("user.dir") + "/localfile.txt";
        try {
            _br = new BufferedReader(new FileReader(_path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getSegment() {
        ArrayList<String> segment = new ArrayList<String>();
        try {
            String line;
            while ((line = _br.readLine()) != null && segment.size() < _segmentLinesCount) {
                segment.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return segment;
    }

}
