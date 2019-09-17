import xyz.drafter.architecture.gupao.orm.demo.model.Member;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangmeng
 * @date 2019/9/12
 * @desciption
 */
public class JdbcTest {

    public static void main(String[] args) {
        try {
            // 1.加载驱动类
            Class.forName("com.mysql.jdbc.Driver");

            // 2.建立连接

            Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.1.249:3306/etc?useUnicode=true&characterEncoding=UTF-8", "root", "123456");

            // 3.创建语句开始事物
            PreparedStatement pstmt = conn.prepareStatement("select ID,CUSTOMER_NO,TEL_NO from AMP_USER");

            // 4.执行语句集
            ResultSet rs = pstmt.executeQuery();

            // 5.获取结果集

            //一共有多少列
            List<Object> result = new ArrayList<>();
            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()){
                Class member = Member.class;
                Object instance = member.newInstance();
                for (int i = 1;i<= columnCount;i++){
                    // 获取列名
                    String columnName = rs.getMetaData().getColumnName(i);
                    Field field = member.getDeclaredField(columnName);
                    field.setAccessible(true);
                    // 数据类型映射
                    Object type = field.getType();
                    if (type == Long.class){
                        field.set(instance,rs.getLong(columnName));
                    }else if (String.class == type){
                        field.set(instance, rs.getString(columnName));
                    }

                }

                result.add(instance);


/*
                System.out.println(rs.getLong("ID"));
                System.out.println(rs.getString("CUSTOMER_NO"));
                System.out.println(rs.getString("TEL_NO"));*/
            }

            System.out.println(Arrays.toString(result.toArray()));
            // =============Begin ORM

            // =============End ORM


            // 6.关闭结果集，关闭语句集，关闭连接
            rs.close();
            pstmt.close();
            conn.close();



        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
