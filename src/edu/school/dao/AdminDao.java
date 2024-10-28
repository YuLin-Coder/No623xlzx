package edu.school.dao;


import edu.school.entity.Admin;
import edu.school.entity.Student;
import edu.school.entity.Teacher;

public interface AdminDao {
    public Admin queryByUsernameAndPwd(String username, String pwd);//根据管理员账号和密码来登录
    public int update(Admin record);//根据id数量
    public Admin queryByUsername(String username);//根据用户名查询
    public Admin query(Integer id);
}
