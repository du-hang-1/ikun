package org.example.test1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** pojo 实体类 ORM object relation model
 * DAO接口
 * @author dh
 * @date 2024/7/22 下午3:22
 */
public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/nuist?useUnicode=true&characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "a12345";
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USERNAME,PASSWORD);
    }
}
