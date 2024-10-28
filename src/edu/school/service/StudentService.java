package edu.school.service;

import edu.school.entity.PageTool;
import edu.school.entity.Student;

import java.util.List;


public interface StudentService {
    /*
     判断用户是否存在,返回true表示学生已经存在，返回false表示该学号可以用
    */
    public boolean existsStuno(String stuno);

    public List<Student> findByMap(String stuno, String realname);//条件查询
    public int queryCount();
    public int update(Student record);//修改
    public int addStudent(Student record);//修改
    public int delete(Integer id);//根据id数量
    public Student queryById(Integer id);//根据id查询
    public Student findByStuno(String stuno);//根据学号和
    public Student login(String stuno, String pwd);//根据学号和密码来登录
    public List<Student> findAllByPage(PageTool pageTool);//分页
}
