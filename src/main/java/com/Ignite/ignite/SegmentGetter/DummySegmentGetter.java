package com.Ignite.ignite.SegmentGetter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class DummySegmentGetter implements SegmentGetter {

    public DummySegmentGetter() {

    }

    @Override
    public List<String> getSegment() {
        String inFile = "localfile.txt";
        BufferedReader br = null;
        ArrayList<String> segment = new ArrayList<String>();
        try {
            br = new BufferedReader(new FileReader(inFile));
            String line;
            while ((line = br.readLine()) != null) {
                segment.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return segment;
    }

}
