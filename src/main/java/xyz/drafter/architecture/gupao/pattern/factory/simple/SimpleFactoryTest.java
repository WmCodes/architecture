package xyz.drafter.architecture.gupao.pattern.factory.simple;

/**
 * @author wangmeng
 * @date 2019/8/22
 * @desciption
 */
public class SimpleFactoryTest {

    public static void main(String[] args) {

        //System.out.println(new Telunsu().getName());
        SimpleFactory factory = new SimpleFactory();
        System.out.println(factory.getMilk("特仑苏").getName());
    }
}
