package com.Ignite.ignite.ResultSetter;

public class DummyResultSetter implements ResultSetter {
    @Override
    public void setResult(long result) {
        System.out.println(String.format(">>> Calculation result is: %d", result));
    }
}
