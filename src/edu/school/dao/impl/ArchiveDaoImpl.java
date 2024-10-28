package edu.school.dao.impl;

import edu.school.dao.ArchiveDao;
import edu.school.entity.Archive;
import edu.school.entity.PageTool;
import edu.school.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ArchiveDaoImpl implements ArchiveDao {
    private QueryRunner runner=new QueryRunner(C3p0Utils.getDs());

    //保存信息
    public int save(Archive record) {
        try {
            //执行插入sql
            runner.update("insert into archive(sid,nation,jg,fname,mname,phone,graduate,note,fj,filename,create_date) values (?,?,?,?,?,?,?,?,?,?,now())",
                    record.getSid(),record.getNation(),record.getJg(),record.getFname(),record.getMname(),record.getPhone(),record.getGraduate(),record.getNote(),record.getFj(),record.getFilename());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

    public static void main(String[] args) {
		ArchiveDaoImpl daoImpl=new ArchiveDaoImpl();
		/*daoImpl.save();*/
			
	}
    public Archive query(Integer id) {//根据id查询
        try {//返回查询的信息
            return runner.query("select * from archive where id=?", new BeanHandler<Archive>(Archive.class),id);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }

    @Override
    public Archive query2(Integer sid) {
        try {//返回查询的信息
            return runner.query("select * from archive where sid=?", new BeanHandler<Archive>(Archive.class),sid);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }


    public int update(Archive record) {//更改信息
        try {//执行更改
            runner.update("update archive set nation=?,jg=?,fname=?,mname=?,phone=?,graduate=?,note=?,fj=?  where id=?",
                    record.getNation(),record.getJg(),record.getFname(),record.getMname(),record.getPhone(),record.getGraduate(),record.getNote(),record.getFj(),record.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;
    }

    @Override
    public List<Archive> getByPage(PageTool pageTool) {
        String sql="select * from archive limit ?,?";
        List<Archive> list=null;
        try {
            list=runner.query(sql,new BeanListHandler<Archive>(Archive.class),pageTool.getStartIndex(),pageTool.getPageSize());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Archive> findByMap(String jg) {
        String sql="select * from archive where 1=1 ";
        List<Archive> list=null;
        //todo 使用JavaBean对象的list集合的实现类 BeanListHandler类封装
        List<String> list1 = new ArrayList<String>();

        if (jg != "") {
            //如果输入的content不为空，那需要进行字符串拼接
            sql += " and jg like  ? ";
            //将用户输入的参数添加到集合
            list1.add("%" + jg + "%");
        }
        Object[] params = list1.toArray();
        try {
            list=runner.query(sql, params, new BeanListHandler<Archive>(Archive.class));
            System.out.println(list.size());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    
    //统计数量
    public int queryCount() {
        String sql="select count(*) from archive";
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
    public List<Archive> findMyArchive(int sid) {
        String sql="select * from archive where sid=? ";
        List<Archive> list=null;
        try {
            list=runner.query(sql,  new BeanListHandler<Archive>(Archive.class),sid);
            System.out.println(list.size());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    //删除信息
    public int delete(Integer id) {
        try {
            //执行删除的sql
            runner.update("delete from archive where id=?",id);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;//删除成功返回1表示结束
    }





}
