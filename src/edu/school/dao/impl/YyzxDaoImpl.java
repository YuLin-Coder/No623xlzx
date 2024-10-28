package edu.school.dao.impl;

import edu.school.dao.YyzxDao;
import edu.school.entity.Yyzx;
import edu.school.entity.PageTool;
import edu.school.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class YyzxDaoImpl implements YyzxDao {
    private QueryRunner runner=new QueryRunner(C3p0Utils.getDs());

    //保存信息
    public int save(Yyzx record) {
        try {
            //执行插入sql
            runner.update("insert into yyzx(s_id,status,tname,bz,yysj) values (?,?,?,?,?)",
                   record.getS_id(),record.getStatus(),record.getTname(),record.getBz(),record.getYysj());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

    public static void main(String[] args) {
		YyzxDaoImpl daoImpl=new YyzxDaoImpl();
		/*daoImpl.save();*/
			
	}
    public Yyzx query(Integer yid) {//根据id查询
        try {//返回查询的信息
            return runner.query("select * from yyzx where yid=?", new BeanHandler<Yyzx>(Yyzx.class),yid);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }



    public int update(Yyzx record) {//更改公告信息
        try {//执行更改
            runner.update("update yyzx set bz=?,yysj=? where yid=?",
                    record.getBz(),record.getYysj(),record.getYid());
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;
    }

    @Override
    public int updateStaus(Yyzx record) {
        try {//执行更改
            runner.update("update yyzx set status=? where yid=?",
                    record.getStatus(),record.getYid());
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;
    }

    @Override
    public  List<Map<String, Object>> findByMap(String yysj) {
        String sql="select y.*,s.* from yyzx y,student s where y.s_id=s.id  ";
        List<Map<String, Object>> list=null;
        //todo 使用JavaBean对象的list集合的实现类 BeanListHandler类封装
        List<String> list1 = new ArrayList<String>();
        if (yysj != "") {
            //如果用户输入的stuno不为空，那需要进行字符串拼接
            sql += "and yysj =  ? ";
            //将用户输入的参数添加到集合
            list1.add( yysj );
        }
        Object[] params = list1.toArray();
        try {
            list=runner.query(sql, params,  new MapListHandler());
            System.out.println(list.size());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }






    
    //统计数量
    public int queryCount() {
        String sql="select count(*) from yyzx";
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
    public List<Map<String, Object>> selectdoubleList(PageTool pageTool) {
        List<Map<String, Object>> list=null;
        String sql="select y.*,s.* from yyzx y,student s where y.s_id=s.id limit ?,? ";
        //todo 使用JavaBean对象的list集合的实现类 BeanListHandler类封装
        try {
            list=runner.query(sql, new MapListHandler(),new Object[] {pageTool.getStartIndex(),pageTool.getPageSize()});
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Yyzx> findMyYyzx(int s_id) {
        String sql="select * from yyzx where s_id=?";
        List<Yyzx> list=null;
        try {
            list=runner.query(sql,new BeanListHandler<Yyzx>(Yyzx.class),s_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> findMyTname(String tname) {
        String sql="select y.*,s.* from yyzx y,student s where y.s_id=s.id   ";
        List<Map<String, Object>> list=null;
        //todo 使用JavaBean对象的list集合的实现类 BeanListHandler类封装
        List<String> list1 = new ArrayList<String>();
        if (tname != "") {
            //如果用户输入的stuno不为空，那需要进行字符串拼接
            sql += "and tname =  ? ";
            //将用户输入的参数添加到集合
            list1.add(tname);
        }
        Object[] params = list1.toArray();
        try {
            list=runner.query(sql, params,  new MapListHandler());
            System.out.println(list.size());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    //删除学生预约咨询信息
    public int delete(Integer yid) {
        try {
            //执行删除的sql
            runner.update("delete from yyzx where yid=?",yid);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;//删除成功返回1表示结束
    }





}
