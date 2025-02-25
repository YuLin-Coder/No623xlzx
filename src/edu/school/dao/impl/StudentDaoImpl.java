package edu.school.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.school.dao.StudentDao;
import edu.school.entity.PageTool;
import edu.school.entity.Student;
import edu.school.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;



public class StudentDaoImpl implements StudentDao {
    private QueryRunner runner=new QueryRunner(C3p0Utils.getDs());
    //根据学号查询
    public Student queryByStuno(String stuno) {
        try {//返回查询的信息
            return runner.query("select * from student where stuno=?", new BeanHandler<Student>(Student.class),stuno);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }

    }

  //返回查询的信息
    public Student queryByStunoAndPwd(String stuno, String pwd) {
        try {
            return runner.query("select * from student where stuno=? and pwd=?", new BeanHandler<Student>(Student.class),stuno,pwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }

    }

    //保存学生信息
    public int save(Student student) {
        try {
            //执行插入sql
            runner.update("insert into student(stuno,realname,pwd,major,sex,bj,photo,createDate) values (?,?,?,?,?,?,?,?)",
                   student.getStuno(),student.getRealname(),student.getPwd(),student.getMajor(),student.getSex(),student.getBj(),student.getPhoto(),student.getCreateDate());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

    public static void main(String[] args) {
		StudentDaoImpl daoImpl=new StudentDaoImpl();
		/*daoImpl.save();*/
			
	}
    public Student query(Integer id) {//根据id查询
        try {//返回查询的信息
            return runner.query("select * from student where id=?", new BeanHandler<Student>(Student.class),id);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }


    public int update(Student student) {//更改学生信息
        try {//执行更改
            runner.update("update student set stuno=?, realname=?,pwd=?,major=?,sex=?,bj=?,photo=? where id=?",
            		 student.getStuno(),student.getRealname(),student.getPwd(),student.getMajor(),student.getSex(),student.getBj(),student.getPhoto(),student.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;
    }


    public List<Student> getPage(int pageNum, int pageSize) {
        String sql="select * from student order by stuno desc limit ?,?";
        int startNo=(pageNum-1)*pageSize;
        List<Student> list=null;
        try {
            list= runner.query(sql, new BeanListHandler<Student>(Student.class),new Object[] {startNo,pageSize});//添加实体类的适配器进行类的反射
            return list;
        } catch (SQLException e) {//捕获异常
            throw new RuntimeException(e);//抛出运行异常
        }
    }


   //查询学生信息
    public List<Student> findByMap(String stuno,String realname) {
        String sql="select * from student where 1=1 ";
        List<Student> list=null;
        //todo 使用JavaBean对象的list集合的实现类 BeanListHandler类封装
        List<String> list1 = new ArrayList<String>();
        if (stuno != "") {
          
            sql += " and stuno like  ? ";
            //将用户输入的参数添加到集合
            list1.add("%" + stuno + "%");
        }
        if (realname != "") {
            //如果用户输入的realname不为空，那需要进行字符串拼接
            sql += " and realname like  ? ";
            //将用户输入的参数添加到集合
            list1.add("%" + realname + "%");
        }
        Object[] params = list1.toArray();
        try {
            list=runner.query(sql, params, new BeanListHandler<Student>(Student.class));
            System.out.println(list.size());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    
    //统计数量
    public int queryCount() {
        String sql="select count(*) from student";
        try {
            Long count =  (Long) runner.query(sql, new ScalarHandler());
            //将long的类型转成int类型
            return count.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

   //删除学生信息
    public int delete(Integer id) {
        try {
            //执行删除的sql
            runner.update("delete from student where id=?",id);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }

        return 1;//删除成功返回1表示结束
    }



    @Override
    public List<Student> findAllByPage(PageTool pageTool) {
        String sql="select * from student limit ?,?";
        List<Student> students=null;
        try {
            students=runner.query(sql,new BeanListHandler<Student>(Student.class),pageTool.getStartIndex(),pageTool.getPageSize());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }


}
