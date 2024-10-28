package edu.school.service.impl;

import edu.school.dao.TeacherDao;
import edu.school.dao.impl.TeacherDaoImpl;
import edu.school.entity.PageTool;
import edu.school.entity.Teacher;
import edu.school.service.TeacherService;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    private TeacherDao dao=new TeacherDaoImpl();
    @Override
    public int addTeacher(Teacher record) {
        return dao.save(record);
    }

    @Override
    public Teacher findById(Integer id) {
        return dao.query(id);
    }

    @Override
    public Teacher findByTname(String tname) {
        return dao.queryByTname(tname);
    }

    @Override
    public int updateTeacher(Teacher record) {
        return dao.update(record);
    }

    @Override
    public List<Teacher> findByMap(String tno, String name) {
        return dao.findByMap(tno,name);
    }

    @Override
    public int deleteTeacher(Integer id) {
        return dao.delete(id);
    }

    @Override
    public int queryCount() {
        return dao.queryCount();
    }

    @Override
    public List<Teacher> findAllByPage(PageTool pageTool) {
        return dao.findAllByPage(pageTool);
    }

    @Override
    public boolean existsTno(String tno) {
        if(dao.queryByTno(tno)==null){
            return false;//表示不可以用
        }
        return true;//表示可以用

    }

    @Override
    public Teacher login(String tno, String pwd) {
        return dao.queryByTnoAndPwd(tno,pwd);
    }

    @Override
    public List<Teacher> findAll() {
        return dao.findAll();
    }

    @Override
    public int updateTx(Teacher record) {
        return dao.updateTx(record);
    }
}
