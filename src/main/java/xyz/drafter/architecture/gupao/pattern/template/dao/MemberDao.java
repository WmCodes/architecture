package xyz.drafter.architecture.gupao.pattern.template.dao;

import xyz.drafter.architecture.gupao.pattern.template.JdbcTemplate;
import xyz.drafter.architecture.gupao.pattern.template.RowMapper;
import xyz.drafter.architecture.gupao.pattern.template.entity.Member;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author wangmeng
 * @date 2019/8/25
 * @desciption
 */
public class MemberDao  {

    private JdbcTemplate jdbcTemplate =  new JdbcTemplate(null);



    public List<?> query(){
        String sql = "select * from t_member";
        return jdbcTemplate.executeQuery(sql, new RowMapper<Member>(){

            @Override
            public Member mapRow(ResultSet rs, int rouNum) throws Exception {
                Member member = new Member();
                member.setUsername(rs.getString("username"));
                member.setPassword(rs.getString("password"));
                member.setAge(rs.getInt("age"));
                member.setAddr(rs.getString("addr"));
                return member;
            }
        },null);
    }




  /*  public Object processResult(ResultSet rs,int rowNum) throws Exception {
        Member member = new Member();
        member.setUsername(rs.getString("username"));
        member.setPassword(rs.getString("password"));
        member.setAge(rs.getInt("age"));
        member.setAddr(rs.getString("addr"));
        return member;
    }*/
}
