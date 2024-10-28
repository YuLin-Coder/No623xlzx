package edu.school.service;

import edu.school.entity.Admin;

public interface AdminService {
    public Admin login(String username, String pwd);//管理员登陆
    public int updateAdmin(Admin record);//管理员修改密码
    public Admin findById(Integer id);
}
