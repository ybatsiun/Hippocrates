package service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.Doctor;
import dao.DoctorDao;

@Transactional
@Service("DoctorsService")
public class DoctorsService implements Serializable{

	

		
		private static final long serialVersionUID = 1L;
		@Autowired
		public DoctorDao doctorDao;

		
		
		@Autowired
		public void setDoctorDao(DoctorDao doctorDao) {
			this.doctorDao = doctorDao;
		}

	public void createDoctor(Doctor doctor){
		doctorDao.createDoctor(doctor);
	}
}
