package xyz.drafter.architecture.gupao.mybatisV1;

/**
 * @author wangmeng
 * @date 2019/9/17
 * @desciption
 */
public interface Executor {
    <T> T query(String statement, String parameter);
}
