package edu.school.dao;

import edu.school.entity.PageTool;
import edu.school.entity.Attendance;

import java.util.List;
//打卡
public interface AttendanceDao {

    public int save(Attendance record);//保存教师考勤信息
    public Attendance query(Integer id);
    public int update(Attendance r);
    public List<Attendance> findByMap(Integer t_id);//条件查询
    public int delete(Integer id);//根据id数量
    public int queryCount();//统计数量
    public List<Attendance> findAllByPage(PageTool pageTool);//分页
}
