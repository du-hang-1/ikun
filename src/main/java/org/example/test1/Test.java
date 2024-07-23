package org.example.test1;


import org.example.dao.StudentDAO;
import org.example.dao.impl.StudentDAOImpl;

/**
 * @author dh
 * @date 2024/7/22 下午2:23
 */
public class Test {
    public static void main(String[] args) {
//        //驱动加载
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        }catch(ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        System.out.println("驱动加载成功");
//        //获取jdbc连接
//        String url = "jdbc:mysql://localhost:3306/nuist?characterEncoding=utf8";
//        String user = "root";
//        String password = "a12345";
//        Connection connection = null;
//        try {
//             connection = DriverManager.getConnection(url, user, password);
//        }catch(SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println("获取连接成功");
//
//        try {
//            Statement s = connection.createStatement();
//            //准备一个sql语句
//            String sql = "insert into stu\n" +
//                    "(name,gender,birthday,addr,qqnumber)\n" +
//                    "VALUES\n" +
//                    "('dhssss','男','2003-05-01','空',2941576609)";
//            //运行sql语句
//            s.executeUpdate(sql);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//            //驱动加载 获取连接 创建编译语句对象 执行语句
//            //驱动加载 连接获取 创建编译语句对象 执行语句 获取查询结果集
//        }
//        Student s1 = new Student(0,"ww","男",new Date(System.currentTimeMillis()),"南京",111);
        StudentDAO studentDAO = new StudentDAOImpl();
//        studentDAO.insert(s1);
//        Student s1 = new Student(6,"dhss","女",new Date(System.currentTimeMillis()),"南京",137);
//        studentDAO.update(s1);
//        System.out.println(studentDAO.count());
        int i = 1;
        System.out.println(studentDAO.findById(i));
    }
}
