package edu.school.dao;

import java.util.List;


import edu.school.entity.PageTool;
import edu.school.entity.Student;

public interface StudentDao {
    public Student queryByStuno(String stuno);//根据学号查询
    public Student queryByStunoAndPwd(String stuno, String pwd);//根据学号和密码来登录
    public int save(Student student);//保存学生信息
    public Student query(Integer id);
    public int update(Student student);
    public List<Student> findByMap(String stuno, String realname);//条件查询
    public int delete(Integer id);//根据id数量
    public int queryCount();//统计数量
    public List<Student> findAllByPage(PageTool pageTool);//分页

}
