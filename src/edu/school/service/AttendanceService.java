package edu.school.service;

import edu.school.entity.Attendance;
import edu.school.entity.PageTool;
import edu.school.entity.Student;

import java.util.List;


public interface AttendanceService {

    public int addAttendance(Attendance record);//保存教师考勤信息
    public Attendance findById(Integer id);
    public int update(Attendance a);
    public List<Attendance> findByMap(Integer t_id);//条件查询
    public int deleteAttendance(Integer id);//根据id数量
    public int queryCount();//统计数量
    public List<Attendance> findAllAttendance(PageTool pageTool);//分页
}
