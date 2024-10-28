package edu.school.dao;

import edu.school.entity.PageTool;
import edu.school.entity.Student;
import edu.school.entity.Teacher;


import java.util.List;
//教师Dao
public interface TeacherDao {
    public Teacher queryByTnoAndPwd(String tno, String pwd);//根据学号和密码来登录
    public int save(Teacher record);//保存教师信息
    public Teacher query(Integer id);
    public Teacher queryByTno(String tno);
    public Teacher queryByTname(String tname);
    public int update(Teacher record);
    public int updateTx(Teacher record);//修改照片
    public List<Teacher> findByMap(String tno, String name);//条件查询
    public int delete(Integer id);//根据id数量
    public int queryCount();//统计数量
    public List<Teacher> findAllByPage(PageTool pageTool);//分页
    public List<Teacher> findAll();

}
