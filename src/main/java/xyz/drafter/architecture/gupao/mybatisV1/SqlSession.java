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

    //
    public <T> T getMapper(Class<T> clazz){
        return configuration.getMapper(clazz,this);
    }

    // sql语句和参数
    public <T> T selectOne(String statement,String parameter){
        return executor.query(statement,parameter);
    }
}
