package edu.school.dao.impl;

import edu.school.dao.TieziDao;
import edu.school.entity.Tiezi;
import edu.school.entity.PageTool;
import edu.school.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TieziDaoImpl implements TieziDao {
    private QueryRunner runner=new QueryRunner(C3p0Utils.getDs());

    //保存信息
    public int save(Tiezi record) {
        try {
            //执行插入sql
            runner.update("insert into tiezi(title,content,sid,create_time) values (?,?,?,now())",
                   record.getTitle(),record.getContent(),record.getSid());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

    public static void main(String[] args) {
		TieziDaoImpl daoImpl=new TieziDaoImpl();
		/*daoImpl.save();*/
			
	}
    public Tiezi query(Integer id) {//根据id查询
        try {//返回查询的信息
            return runner.query("select * from tiezi where id=?", new BeanHandler<Tiezi>(Tiezi.class),id);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }



    public int update(Tiezi record) {//更改公告信息
        try {//执行更改
            runner.update("update tiezi set title=?, content=?  where id=?",
                    record.getTitle(),record.getContent(),record.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;
    }

    @Override
    public List<Tiezi> getByPage(PageTool pageTool) {
        String sql="select * from tiezi limit ?,?";
        List<Tiezi> list=null;
        try {
            list=runner.query(sql,new BeanListHandler<Tiezi>(Tiezi.class),pageTool.getStartIndex(),pageTool.getPageSize());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Tiezi> findByMap(String title,String content) {
        String sql="select * from tiezi where 1=1 ";
        List<Tiezi> list=null;
        //todo 使用JavaBean对象的list集合的实现类 BeanListHandler类封装
        List<String> list1 = new ArrayList<String>();
        if (title != "") {
            //如果输入的title不为空，那需要进行字符串拼接
            sql += " and title like  ? ";
            //将用户输入的参数添加到集合
            list1.add("%" + content + "%");
        }
        if (content != "") {
            //如果输入的content不为空，那需要进行字符串拼接
            sql += " and content like  ? ";
            //将用户输入的参数添加到集合
            list1.add("%" + content + "%");
        }
        Object[] params = list1.toArray();
        try {
            list=runner.query(sql, params, new BeanListHandler<Tiezi>(Tiezi.class));
            System.out.println(list.size());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    
    //统计数量
    public int queryCount() {
        String sql="select count(*) from tiezi";
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
    public List<Tiezi> findMyTiezi(int sid) {
        String sql="select * from tiezi where sid=? ";
        List<Tiezi> list=null;
        try {
            list=runner.query(sql,  new BeanListHandler<Tiezi>(Tiezi.class),sid);
            System.out.println(list.size());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Tiezi> fidAll() {
        String sql="select * from tiezi";
        List<Tiezi> list=null;
        try {
            list=runner.query(sql,  new BeanListHandler<Tiezi>(Tiezi.class));
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
            runner.update("delete from tiezi where id=?",id);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;//删除成功返回1表示结束
    }





}
