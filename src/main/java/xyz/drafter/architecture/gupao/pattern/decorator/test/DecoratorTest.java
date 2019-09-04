package xyz.drafter.architecture.gupao.pattern.decorator.test;

import java.io.DataInputStream;
import java.io.FilterInputStream;
import java.io.InputStream;

/**
 * @author wangmeng
 * @date 2019/9/2
 * @desciption 装饰器模式
 */
public class DecoratorTest {

    public static void main(String[] args) {
        InputStream in = null;
        // DataInputStream
        FilterInputStream fis = new DataInputStream(in);

    }
}
