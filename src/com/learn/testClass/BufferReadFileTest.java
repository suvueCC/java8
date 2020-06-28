package com.learn.testClass;

import com.learn.interfaces.BufferReaderProcessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 读取文件的行数据
 *
 * @author suvue
 * @date 2020/4/24
 */
public class BufferReadFileTest {
    public static void main(String[] args) throws IOException {

        System.out.println(readFile((BufferedReader br) ->br.readLine()));
        System.out.println(readFile((BufferedReader br) ->br.readLine()+br.readLine()));
    }

    private static String readFile(BufferReaderProcessor processor) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("Z:\\tmp\\test.txt"))) {
            return processor.read(reader);
        }
    }
}
