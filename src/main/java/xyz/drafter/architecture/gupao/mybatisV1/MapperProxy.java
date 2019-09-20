package xyz.drafter.architecture.gupao.mybatisV1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wangmeng
 * @date 2019/9/17
 * @desciption
 */
public class MapperProxy implements InvocationHandler {

    private SqlSession sqlSession;

    public MapperProxy(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getDeclaringClass().getName().equals(Configuration.TestMapperXml.namespace)) {
            String sql = Configuration.TestMapperXml.methodSqlMapping.get(method.getName());
            return sqlSession.selectOne(sql,String.valueOf(args[0]));
        }


        return method.invoke(this, args);
    }


}
