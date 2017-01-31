package service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.Doctor;
import dao.DoctorDao;

@Transactional
@Service("DoctorsService")
public class DoctorsService implements Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
	public DoctorDao doctorDao;

	@Autowired
	public void setDoctorDao(DoctorDao doctorDao) {
		this.doctorDao = doctorDao;
	}

	public void createDoctor(Doctor doctor) {
		doctorDao.createDoctor(doctor);
	}

	public List<Doctor> showSchedule(String username) {
		System.out.println("Service:redirecting to DAO... ");
		List<Doctor> schedule = doctorDao.showSchedule(username);
		return schedule;
	}

	public List<Doctor> showDoctorsList() {
		List<Doctor> doctorsList = doctorDao.showDoctors();
		return doctorsList;
	}

	public void bookAnAppointment(String doctorsUsername, String patientUsername, String dayAndTime,String complain) {
		doctorDao.bookAnAppointment(doctorsUsername, patientUsername, dayAndTime,complain);
		
	}

	public List<Doctor> showDoctorsListForAdmin() {
		List<Doctor> doctorsListForAdmin = doctorDao.showDoctorsForAdmin();
		return doctorsListForAdmin;
	}

	public List<Doctor> showSchedules() {
		List<Doctor> scheduleForAdmin = doctorDao.showSchedules();
		return scheduleForAdmin;
	}
}
