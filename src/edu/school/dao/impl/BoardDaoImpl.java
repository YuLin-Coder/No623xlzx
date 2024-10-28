package edu.school.dao.impl;

import edu.school.dao.BoardDao;
import edu.school.entity.PageTool;
import edu.school.entity.Board;
import edu.school.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BoardDaoImpl implements BoardDao {
    private QueryRunner runner=new QueryRunner(C3p0Utils.getDs());

    //保存信息
    public int save(Board record) {
        try {
            //执行插入sql
            runner.update("insert into board(title,content,fbsj,editor) values (?,?,?,?)",
                   record.getTitle(),record.getContent(),record.getFbsj(),record.getEditor());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

    public static void main(String[] args) {
		BoardDaoImpl daoImpl=new BoardDaoImpl();
		/*daoImpl.save();*/
			
	}
    public Board query(Integer id) {//根据id查询
        try {//返回查询的信息
            return runner.query("select * from board where id=?", new BeanHandler<Board>(Board.class),id);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }



    public int update(Board record) {//更改公告信息
        try {//执行更改
            runner.update("update board set title=?, content=?,editor=? where id=?",
                    record.getTitle(),record.getContent(),record.getEditor(),record.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;
    }
   //查询公告信息
    public List<Board> findByMap(String title,String content) {
        String sql="select * from board where 1=1 ";
        List<Board> list=null;
        //todo 使用JavaBean对象的list集合的实现类 BeanListHandler类封装
        List<String> list1 = new ArrayList<String>();
        if (title != "") {
          
            sql += " and title like  ? ";
            //将输入的参数添加到集合
            list1.add("%" + title + "%");
        }
        if (content != "") {
            //如果输入的content不为空，那需要进行字符串拼接
            sql += " and content like  ? ";
            //将用户输入的参数添加到集合
            list1.add("%" + content + "%");
        }
        Object[] params = list1.toArray();
        try {
            list=runner.query(sql, params, new BeanListHandler<Board>(Board.class));
            System.out.println(list.size());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    
    //统计数量
    public int queryCount() {
        String sql="select count(*) from board";
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
            runner.update("delete from board where id=?",id);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;//删除成功返回1表示结束
    }



    @Override
    public List<Board> findAllByPage(PageTool pageTool) {
        String sql="select * from board limit ?,?";
        List<Board> Boards=null;
        try {
            Boards=runner.query(sql,new BeanListHandler<Board>(Board.class),pageTool.getStartIndex(),pageTool.getPageSize());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Boards;
    }


}
