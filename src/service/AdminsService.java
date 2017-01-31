package service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.Admin;
import dao.AdminDao;

@Transactional
@Service("AdminsService")
public class AdminsService implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Autowired
	public AdminDao adminDao;
	
	@Autowired
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	public void createAdmin(Admin admin) {
		adminDao.createDoctor(admin);
	}
	
}
