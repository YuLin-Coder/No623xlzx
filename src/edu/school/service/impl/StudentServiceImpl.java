package edu.school.service.impl;

import edu.school.dao.StudentDao;
import edu.school.dao.impl.StudentDaoImpl;
import edu.school.entity.PageTool;
import edu.school.entity.Student;
import edu.school.service.StudentService;

import java.util.List;



public class StudentServiceImpl implements StudentService {
    private StudentDao dao=new StudentDaoImpl();

    public boolean existsStuno(String stuno) {
        if(dao.queryByStuno(stuno)==null){
            return false;//表示不可以用
        }
        return true;//表示可以用

    }


    public List<Student> findByMap(String stuno,String realname) {
        return dao.findByMap(stuno,realname);
    }

    
    public int queryCount() {
        return dao.queryCount();
    }

    
    public int update(Student record) {
        return dao.update(record);
    }

   
    public int delete(Integer id) {
        return dao.delete(id);
    }

    
    public Student queryById(Integer id) {
        return dao.query(id);
    }

   
    public Student findByStuno(String stuno) {
        return dao.queryByStuno(stuno);
    }


	public Student login(String stuno, String pwd) {
		// TODO Auto-generated method stub
		return dao.queryByStunoAndPwd(stuno, pwd);
	}


    @Override
    public List<Student> findAllByPage(PageTool pageTool) {
        return dao.findAllByPage(pageTool);
    }


    public int addStudent(Student record) {
		// TODO Auto-generated method stub
		return dao.save(record);
	}





	


	
}
