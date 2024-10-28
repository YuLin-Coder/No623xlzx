package edu.school.service.impl;


import edu.school.dao.AdminDao;
import edu.school.dao.impl.AdminDaoImpl;
import edu.school.entity.Admin;
import edu.school.service.AdminService;

public class AdminServiceImpl implements AdminService {
	private AdminDao dao=new AdminDaoImpl();

	

	
	public int updateAdmin(Admin record) {
		// TODO Auto-generated method stub
		return dao.update(record);
	}

	@Override
	public Admin findById(Integer id) {
		return dao.query(id);
	}


	public Admin login(String username, String pwd) {
		// TODO Auto-generated method stub
		return dao.queryByUsernameAndPwd(username, pwd);
	}

}
