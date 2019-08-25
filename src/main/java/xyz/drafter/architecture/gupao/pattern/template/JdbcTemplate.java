package xyz.drafter.architecture.gupao.pattern.template;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangmeng
 * @date 2019/8/25
 * @desciption
 */
public  class JdbcTemplate {


    private DataSource dataSource;
    public JdbcTemplate(DataSource dataSource){
        this.dataSource = dataSource;
    }

    private Connection getConnection() throws Exception{
        return this.dataSource.getConnection();
    }

    private PreparedStatement createPreparedStatement (Connection conn,String sql) throws Exception{
        return conn.prepareStatement(sql);
    }

    private ResultSet executeQuery(PreparedStatement pstmt,Object[] values) throws SQLException {
        for (int i = 0;i<values.length;i++){
            pstmt.setObject(i, values[i]);
        }
        return pstmt.executeQuery();
    }

    private void closeStatement(Statement stmt) throws Exception{
            stmt.close();

    }

    private void closeResult(ResultSet rs) throws Exception{
            rs.close();

    }

    private void closeConnection(Connection connection) throws Exception {

    }

    private List<?> parseResultSet(ResultSet rs,RowMapper rowMapper) throws Exception{
        List<Object> result = new ArrayList<>();
        int rowNum = 1;
        while (rs.next()){
            result.add(rowMapper.mapRow(rs,rowNum++));
        }
        return result;
    }


    public List<?>  executeQuery(String sql,RowMapper<?> rowMapper,Object[] values){

        try {

            // 获取连接
            Connection conn = this.getConnection();
            // 创建语句集
            PreparedStatement pstmt = this.createPreparedStatement(conn, sql);
            // 执行语句集，获得结果集
            ResultSet rs = this.executeQuery(pstmt, values);
                /*   List<Object> result = new ArrayList<>();
            int rowNum = 1;
     while (rs.next()){
                result.add(processResult(rs,rowNum++));
            }*/
            //解析语句集
            List<?> result = this.parseResultSet(rs, rowMapper);


            //关闭结果集
           this.closeResult(rs);
            // 关闭语句集
           this.closeStatement(pstmt);
            //关闭连接
            this.closeConnection(conn);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    //public abstract Object processResult(ResultSet rs,int rowNum) throws Exception;


}
