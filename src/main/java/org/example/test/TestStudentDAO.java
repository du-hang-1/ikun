package org.example.test;


import org.example.dao.StudentDAO;
import org.example.dao.impl.StudentDAOImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/** 测试类 用于DAO接口测试
 * @Test 将指定方法指定为测试方法，可以不依赖于main方法直接运行
 * @Before 在所有测试方法运行之前执行的代码 一般用于环境的初始化
 * @After 在所有测试方法运行之后执行的代码 一般用于资源回收
 * @author dh
 * @date 2024/7/23 上午11:02
 */
public class TestStudentDAO {
    private StudentDAO studentDAO;
    @Before
    public void init(){
        studentDAO = new StudentDAOImpl();
    }
    @Test
    public void testFindById(){
        Assert.assertNotNull(studentDAO.findById(1));
//        System.out.println(studentDAO.findById(1));
    }
    @Test
    public void testCount(){
        Assert.assertEquals(7L,(long)studentDAO.count());
    }
    @Test
    public void testFindAll(){
        Assert.assertNotNull(studentDAO.findAll());
    }
    @Test
    public void testFindByNameLike(){
        Assert.assertNotNull(studentDAO.findByNameLike("d"));
    }
    @Test
    public void testFindByNameLikeWithLimit(){
        Assert.assertNotNull(studentDAO.findByNameLikeWithLimit("d",0,2));
    }
    @Test
    public void testFindWithLimit(){
        Assert.assertNotNull(studentDAO.findWithLimit(0,3));
    }
}
