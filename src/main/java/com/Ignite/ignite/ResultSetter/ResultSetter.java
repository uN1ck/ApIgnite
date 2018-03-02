package com.Ignite.ignite.ResultSetter;

/**
 * Интерфейс передачи результата из {@link com.Ignite.ignite.Overseer.Overseer Обсчетчика}
 */
public interface ResultSetter {

    void setResult(long result);
}
