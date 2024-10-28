package edu.school.dao.impl;

import edu.school.dao.AttendanceDao;
import edu.school.dao.AttendanceDao;
import edu.school.entity.PageTool;
import edu.school.entity.Attendance;
import edu.school.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AttendanceDaoImpl implements AttendanceDao {
    private QueryRunner runner=new QueryRunner(C3p0Utils.getDs());


    //保存学生信息
    public int save(Attendance record) {
        try {
            //执行插入sql
            runner.update("insert into attendance(t_id,status,create_time) values (?,?,now())",
                  record.getT_id(),record.getStatus());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

    public static void main(String[] args) {
		AttendanceDaoImpl daoImpl=new AttendanceDaoImpl();
		/*daoImpl.save();*/
			
	}
    public Attendance query(Integer id) {//根据id查询
        try {//返回查询的信息
            return runner.query("select * from attendance where id=?", new BeanHandler<Attendance>(Attendance.class),id);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }


    public int update(Attendance a) {//更改学生信息
        try {//执行更改
            runner.update("update attendance set status=?  where id=?",
            		a.getStatus(),a.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;
    }


    public List<Attendance> getPage(int pageNum, int pageSize) {
        String sql="select * from attendance order by create_time desc limit ?,?";
        int startNo=(pageNum-1)*pageSize;
        List<Attendance> list=null;
        try {
            list= runner.query(sql, new BeanListHandler<Attendance>(Attendance.class),new Object[] {startNo,pageSize});//添加实体类的适配器进行类的反射
            return list;
        } catch (SQLException e) {//捕获异常
            throw new RuntimeException(e);//抛出运行异常
        }
    }


   //查询打卡记录
    public List<Attendance> findByMap(Integer t_id) {
        String sql="select * from attendance where t_id =? ";
        List<Attendance> list=null;

        try {
            list=runner.query(sql, t_id, new BeanListHandler<Attendance>(Attendance.class));
            System.out.println(list.size());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    
    //统计数量
    public int queryCount() {
        String sql="select count(*) from attendance";
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
            runner.update("delete from attendance where id=?",id);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }

        return 1;//删除成功返回1表示结束
    }



    @Override
    public List<Attendance> findAllByPage(PageTool pageTool) {
        String sql="select * from attendance limit ?,?";
        List<Attendance> Attendances=null;
        try {
            Attendances=runner.query(sql,new BeanListHandler<Attendance>(Attendance.class),pageTool.getStartIndex(),pageTool.getPageSize());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Attendances;
    }


}
