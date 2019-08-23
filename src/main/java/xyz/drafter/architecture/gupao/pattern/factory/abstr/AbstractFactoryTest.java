package xyz.drafter.architecture.gupao.pattern.factory.abstr;

/**
 * @author wangmeng
 * @date 2019/8/23
 * @desciption
 */
public class AbstractFactoryTest {

    public static void main(String[] args) {

        MilkFactory factory = new MilkFactory();
        System.out.println(factory.getMengniu());

    }
}
