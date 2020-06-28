package com.learn.interfaces;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * 定义函数式接口
 *
 * @author suvue
 * @date 2020/4/24
 */
@FunctionalInterface
public interface BufferReaderProcessor {
    /**
     * 读取文件数据
     */
    String read(BufferedReader reader) throws IOException;
}
