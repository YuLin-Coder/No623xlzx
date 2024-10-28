package edu.school.dao.impl;

import edu.school.dao.MessagesDao;
import edu.school.entity.PageTool;
import edu.school.entity.Messages;
import edu.school.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MessagesDaoImpl implements MessagesDao {
    private QueryRunner runner=new QueryRunner(C3p0Utils.getDs());

    //保存信息
    public int save(Messages record) {
        try {
            //执行插入sql
            runner.update("insert into messages(sid,content,create_time) values (?,?,now())",
                    record.getSid(),record.getContent());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

    public static void main(String[] args) {
		MessagesDaoImpl daoImpl=new MessagesDaoImpl();
		/*daoImpl.save();*/

	}
    public Messages query(Integer id) {//根据id查询
        try {//返回查询的信息
            return runner.query("select * from messages where id=?", new BeanHandler<Messages>(Messages.class),id);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }



    public int update(Messages record) {//更改信息
        try {//执行更改
            runner.update("update messages set  content=?  where id=?",
                   record.getContent(),record.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;
    }

    @Override
    public List<Messages> getByPage(PageTool pageTool) {
        String sql="select * from messages limit ?,?";
        List<Messages> list=null;
        try {
            list=runner.query(sql,new BeanListHandler<Messages>(Messages.class),pageTool.getStartIndex(),pageTool.getPageSize());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Messages> findByMap(String content) {
        String sql="select * from messages where 1=1 ";
        List<Messages> list=null;
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
            list=runner.query(sql, params, new BeanListHandler<Messages>(Messages.class));
            System.out.println(list.size());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }




    //统计数量
    public int queryCount() {
        String sql="select count(*) from messages";
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
    public List<Messages> findMyMessages(int sid) {

        String sql="select * from messages where sid=? ";
        List<Messages> list=null;
        try {
            list=runner.query(sql, new BeanListHandler<Messages>(Messages.class),sid );
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
            runner.update("delete from messages where id=?",id);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;//删除成功返回1表示结束
    }





}
