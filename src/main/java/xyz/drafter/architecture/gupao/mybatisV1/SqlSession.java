package xyz.drafter.architecture.gupao.mybatisV1;

/**
 * @author wangmeng
 * @date 2019/9/17
 * @desciption
 */
public class SqlSession {

    Configuration configuration;
    Executor executor;

    public SqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }
}
