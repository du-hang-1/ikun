package org.example.dao.impl;

import org.example.dao.StudentDAO;
import org.example.pojo.Student;
import org.example.test1.DBUtil;


import java.sql.*;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;


import java.util.List;

/** qqqqqq
 * @author dh
 * @date 2024/7/22 下午3:53
 */
public class StudentDAOImpl implements StudentDAO {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public void insert(Student student) {
        Connection c = null;
        try {
            //传参麻烦
            //性能较差
            //存在sql注入攻击的问题

            //PreparedStatement
            //先编译 后传参 效率更高
            //传参方便
            //有效防止sql注入攻击的问题
            c = DBUtil.getConnection();
            Statement st = c.createStatement();
            String sql = "insert into student(name,gender,birthday,addr,qqnumber)"+
                          "values('%s','%s','%s','%s','%d')";
            sql= String.format(sql,student.getName(),student.getGender(),sdf.format(student.getBirthday()),
                    student.getAddr(),student.getQqnumber());
            st.executeUpdate(sql);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        Connection c = null;
        String sql = "delete from student where id=?";
        try {
            //关闭事务的自动提交
            c.setAutoCommit(false);
            c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            c.commit();
        }catch (SQLException e){
            try {
                c.rollback();
            }catch (SQLException e1){
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(Student student) {
        String sql="update student set name=?,gender=?,birthday=?,addr=?,qqnumber=? where id=?";
        try(Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            //给sql语句进行参数传递
            ps.setString(1,student.getName());
            ps.setString(2,student.getGender());
            ps.setDate(3, new Date(student.getBirthday().getTime()));
            ps.setString(4,student.getAddr());
            ps.setLong(5,student.getQqnumber());
            ps.setInt(6,student.getId());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Integer count() {
        String sql = "select count(*) from student";
        int count = 0;
        try(Connection c = DBUtil.getConnection();
           PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                count = rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Student findById(Integer id) {
        String sql = "select * from student where id=?";
        Student student = null;
        try(Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setBirthday(rs.getDate("birthday"));
                student.setAddr(rs.getString("addr"));
                student.setQqnumber(rs.getLong("qqnumber"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public List<Student> findAll() {
        return findWithLimit(0,Integer.MAX_VALUE);
    }

    @Override
    public List<Student> findByNameLike(String name) {
        return findByNameLikeWithLimit(name,0,Integer.MAX_VALUE);
    }

    @Override
    public List<Student> findByNameLikeWithLimit(String name, int start, int limit) {
        String sql = "select * from student where name like concat('%',?,'%') limit ?,?";
        List<Student> list = new ArrayList<>();
        try(Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,name);
            ps.setInt(2,start);
            ps.setInt(3,limit);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setBirthday(rs.getDate("birthday"));
                student.setAddr(rs.getString("addr"));
                student.setQqnumber(rs.getLong("qqnumber"));
                list.add(student);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
         return list.size()==0?null:list;
    }

    @Override
    public List<Student> findWithLimit(int start, int limit) {
        String sql = "select * from student limit ?,?";
        List<Student> list = new ArrayList<>();
        try(Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,start);
            ps.setInt(2,limit);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setBirthday(rs.getDate("birthday"));
                student.setAddr(rs.getString("addr"));
                student.setQqnumber(rs.getLong("qqnumber"));
                list.add(student);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
