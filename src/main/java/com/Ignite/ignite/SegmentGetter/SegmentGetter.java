package com.Ignite.ignite.SegmentGetter;

import java.io.IOException;
import java.util.List;

public interface SegmentGetter {

    /**
     * Метод получения следующего сегмента файла
     * <p>
     * Сегментом является список строк файла, если получен пустой список,
     * то считается что файл закончился и сегментов больше не будет.
     *
     * @return список строк - сегмент файла
     */
    List<String> getSegment();


}
