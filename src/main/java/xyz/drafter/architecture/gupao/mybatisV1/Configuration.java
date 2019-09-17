package xyz.drafter.architecture.gupao.mybatisV1;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangmeng
 * @date 2019/9/17
 * @desciption
 */
public class Configuration {


    public <T> T getMapper(Class<T> clazz,SqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, new MapperProxy(sqlSession));
    }


    static class TestMapperXml{
        public static final String namespace = "xyz.drafter.architecture.gupao.mybatisV1.TestMapper";

        public static final Map<String,String> methodSqlMapping = new HashMap<>();
        static {

            methodSqlMapping.put("selectByPrimaryKay", "select * from test where id = %d");
        }
    }
}
