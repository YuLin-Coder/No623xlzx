package edu.school.service;

import edu.school.entity.PageTool;
import edu.school.entity.Teacher;

import java.util.List;

//教师Dao
public interface TeacherService {

    public int addTeacher(Teacher record);//保存教师信息
    public Teacher findById(Integer id);
    public Teacher findByTname(String tname);
    public int updateTeacher(Teacher record);
    public List<Teacher> findByMap(String tno, String name);//条件查询
    public int deleteTeacher(Integer id);//根据id数量
    public int queryCount();//统计数量
    public List<Teacher> findAllByPage(PageTool pageTool);//分页
    public boolean existsTno(String tno);//判断是否出现重复的教师
    public Teacher login(String tno, String pwd);//根据工号和密码来登录
    public List<Teacher> findAll();
    public int updateTx(Teacher record);//修改照片
}
