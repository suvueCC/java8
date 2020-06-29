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
        //try-with-resources的用法就是，在try关键字的后面跟一个括号
        // 把需要关闭的资源定义在括号内。在try块执行完之后会自动的释放掉资源。
        try (BufferedReader reader = new BufferedReader(new FileReader("Z:\\tmp\\test.txt"))) {
            return processor.read(reader);
        }
    }
}
