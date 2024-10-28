package edu.school.service.impl;

import edu.school.dao.AttendanceDao;
import edu.school.dao.StudentDao;
import edu.school.dao.impl.AttendanceDaoImpl;
import edu.school.dao.impl.StudentDaoImpl;
import edu.school.entity.Attendance;
import edu.school.entity.PageTool;
import edu.school.entity.Student;
import edu.school.service.AttendanceService;
import edu.school.service.StudentService;

import java.util.List;


public class AttendanceServiceImpl implements AttendanceService {
    private AttendanceDao dao=new AttendanceDaoImpl();


    @Override
    public int addAttendance(Attendance record) {
        return dao.save(record);
    }

    @Override
    public Attendance findById(Integer id) {
        return dao.query(id);
    }

    @Override
    public int update(Attendance a) {
        return dao.update(a);
    }

    @Override
    public List<Attendance> findByMap(Integer t_id) {
        return dao.findByMap(t_id);
    }

    @Override
    public int deleteAttendance(Integer id) {
        return dao.delete(id);
    }

    @Override
    public int queryCount() {
        return dao.queryCount();
    }

    @Override
    public List<Attendance> findAllAttendance(PageTool pageTool) {
        return dao.findAllByPage(pageTool);
    }
}
