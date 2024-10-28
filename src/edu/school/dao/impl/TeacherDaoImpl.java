package edu.school.dao.impl;

import edu.school.dao.TeacherDao;
import edu.school.entity.PageTool;
import edu.school.entity.Student;
import edu.school.entity.Teacher;
import edu.school.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TeacherDaoImpl implements TeacherDao {
    private QueryRunner runner=new QueryRunner(C3p0Utils.getDs());

    @Override
    public Teacher queryByTnoAndPwd(String tno, String pwd) {
        try {//返回查询的信息
            return runner.query("select * from teacher where tno=? and pwd=?", new BeanHandler<Teacher>(Teacher.class),tno,pwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }

    //保存信息
    public int save(Teacher record) {
        try {
            //执行插入sql
            runner.update("insert into teacher(tno,pwd,name,sex,major,detail,phone,imgUrl) values (?,?,?,?,?,?,?,?)",
                   record.getTno(),record.getPwd(),record.getName(),record.getSex(),record.getMajor(),record.getDetail(),record.getPhone(),record.getImgUrl());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

    public static void main(String[] args) {
		TeacherDaoImpl daoImpl=new TeacherDaoImpl();
		/*daoImpl.save();*/

	}
    public Teacher query(Integer id) {//根据id查询
        try {//返回查询的信息
            return runner.query("select * from teacher where id=?", new BeanHandler<Teacher>(Teacher.class),id);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }

    @Override
    public Teacher queryByTno(String tno) {
        try {//返回查询的信息
            return runner.query("select * from teacher where tno=?", new BeanHandler<Teacher>(Teacher.class),tno);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }

    @Override
    public Teacher queryByTname(String tname) {
        try {//返回查询的信息
            return runner.query("select * from teacher where name=?", new BeanHandler<Teacher>(Teacher.class),tname);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }


    public int update(Teacher record) {//更改教师信息
        try {//执行更改
            runner.update("update teacher set tno=?, pwd=?,name=?,sex=?,major=?,detail=?,phone=?,imgUrl=? where id=?",
                    record.getTno(),record.getPwd(),record.getName(),record.getSex(),record.getMajor(),record.getDetail(),record.getPhone(),record.getImgUrl(),record.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;
    }

    @Override
    public int updateTx(Teacher record) {
        try {//执行更改
            runner.update("update teacher set imgUrl=? where id=?",
                    record.getImgUrl(),record.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;
    }

    //查询教师信息
    public List<Teacher> findByMap(String tno,String name) {
        String sql="select * from teacher where 1=1 ";
        List<Teacher> list=null;
        //todo 使用JavaBean对象的list集合的实现类 BeanListHandler类封装
        List<String> list1 = new ArrayList<String>();
        if (tno != "") {

            sql += " and tno like  ? ";
            //将用户输入的参数添加到集合
            list1.add("%" + tno + "%");
        }
        if (name != "") {
            //如果用户输入的name不为空，那需要进行字符串拼接
            sql += " and name like  ? ";
            //将用户输入的参数添加到集合
            list1.add("%" + name + "%");
        }
        Object[] params = list1.toArray();
        try {
            list=runner.query(sql, params, new BeanListHandler<Teacher>(Teacher.class));
            System.out.println(list.size());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    //统计数量
    public int queryCount() {
        String sql="select count(*) from teacher";
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
            runner.update("delete from teacher where id=?",id);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;//删除成功返回1表示结束
    }



    @Override
    public List<Teacher> findAllByPage(PageTool pageTool) {
        String sql="select * from teacher limit ?,?";
        List<Teacher> teachers=null;
        try {
            teachers=runner.query(sql,new BeanListHandler<Teacher>(Teacher.class),pageTool.getStartIndex(),pageTool.getPageSize());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    @Override
    public List<Teacher> findAll() {
        String sql="select * from teacher ";
        List<Teacher> teachers=null;
        try {
            teachers=runner.query(sql,new BeanListHandler<Teacher>(Teacher.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }


}
