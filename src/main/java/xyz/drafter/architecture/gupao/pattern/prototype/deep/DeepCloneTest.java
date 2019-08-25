package xyz.drafter.architecture.gupao.pattern.prototype.deep;

/**
 * @author wangmeng
 * @date 2019/8/24
 * @desciption
 */
public class DeepCloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        QiTianDaSheng qiTianDaSheng = new QiTianDaSheng();
        QiTianDaSheng clone = (QiTianDaSheng)qiTianDaSheng.clone();

        System.out.println(qiTianDaSheng == clone);
        System.out.println(qiTianDaSheng.jinGuBang == clone.jinGuBang);

    }
}
