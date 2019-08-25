package xyz.drafter.architecture.gupao.pattern.template;

import java.sql.ResultSet;

/**
 * @author wangmeng
 * @date 2019/8/25
 * @desciption
 */
public interface RowMapper<T> {

    public T mapRow(ResultSet rs,int rouNum) throws Exception;
}
