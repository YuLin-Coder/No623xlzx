package edu.school.dao.impl;

import edu.school.dao.ReplyDao;
import edu.school.entity.Reply;
import edu.school.entity.PageTool;
import edu.school.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ReplyDaoImpl implements ReplyDao {
    private QueryRunner runner=new QueryRunner(C3p0Utils.getDs());

    //保存信息
    public int save(Reply record) {
        try {
            //执行插入sql
            runner.update("insert into reply(sid,teacher_id,c_id,m_id,content,flag,create_time) values (?,?,?,?,?,?,now())",
                    record.getSid(),record.getTeacher_id(),record.getC_id(),record.getM_id(),record.getContent(),record.getFlag());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

    public static void main(String[] args) {
		ReplyDaoImpl daoImpl=new ReplyDaoImpl();
		/*daoImpl.save();*/
			
	}
    public Reply query(Integer id) {//根据id查询
        try {//返回查询的信息
            return runner.query("select * from reply where id=?", new BeanHandler<Reply>(Reply.class),id);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }



    public int update(Reply record) {//更改信息
        try {//执行更改
            runner.update("update reply set content=?  where id=?",
                  record.getContent(),record.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;
    }

    @Override
    public List<Reply> getByPage(PageTool pageTool) {
        String sql="select * from reply limit ?,?";
        List<Reply> list=null;
        try {
            list=runner.query(sql,new BeanListHandler<Reply>(Reply.class),pageTool.getStartIndex(),pageTool.getPageSize());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Reply> findByMap(String content) {
        String sql="select * from reply where 1=1 ";
        List<Reply> list=null;
        //todo 使用JavaBean对象的list集合的实现类 BeanListHandler类封装
        List<String> list1 = new ArrayList<String>();

        if (content != "") {
            //如果输入的content不为空，那需要进行字符串拼接
            sql += " and content like  ? ";
            //将用户输入的参数添加到集合
            list1.add("%" + content + "%");
        }
        Object[] params = list1.toArray();
        try {
            list=runner.query(sql, params, new BeanListHandler<Reply>(Reply.class));
            System.out.println(list.size());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    
    //统计数量
    public int queryCount() {
        String sql="select count(*) from reply";
        try {
            Long count =  (Long) runner.query(sql, new ScalarHandler());
            //将long的类型转成int类型
            return count.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Reply> findMyReply(int sid) {
        String sql="select * from reply where sid=? ";
        List<Reply> list=null;
        try {
            list=runner.query(sql,  new BeanListHandler<Reply>(Reply.class),sid);
            System.out.println(list.size());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Reply> findTeacherReply(int teacher_id) {
        String sql="select * from reply where teacher_id=? ";
        List<Reply> list=null;
        try {
            list=runner.query(sql,  new BeanListHandler<Reply>(Reply.class),teacher_id);
            System.out.println(list.size());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    //删除学生信息
    public int delete(Integer id) {
        try {
            //执行删除的sql
            runner.update("delete from reply where id=?",id);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;//删除成功返回1表示结束
    }





}
