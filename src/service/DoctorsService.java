package service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.Calendar;
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

	public List<Timestamp> showScheduleForNextNextWeek(String username) {
		System.out.println("Service:redirecting to DAO... ");
		List<Timestamp> schedule = doctorDao.showScheduleTimeForNextNextWeek(username);
		return schedule;
	}
	
	public List<Calendar> showScheduleForCurrent_and_NextWeek(String username) {
		System.out.println("Service:redirecting to DAO... ");
		List<Calendar> schedule = doctorDao.showScheduleForCurrent_and_NextWeek(username);
		return schedule;
	}

	public List<Calendar> showScheduleForCurrent_Next_and_NextNext_Weeks(String username) {
		System.out.println("Service:redirecting to DAO... ");
		List<Calendar> schedule = doctorDao.showScheduleForCurrent_Next_and_NextNext_Weeks(username);
		return schedule;
	}

	public List<Doctor> showDoctorsList() {
		List<Doctor> doctorsList = doctorDao.showDoctors();
		return doctorsList;
	}

	

	public List<Doctor> showDoctorsListForAdmin() {
		List<Doctor> doctorsListForAdmin = doctorDao.showDoctorsForAdmin();
		return doctorsListForAdmin;
	}

	public void bookAnAppointmentFor(String doctorUsername, long dateTime, String complain, String patientUsername) {

		doctorDao.bookAnAppointmentFor ( doctorUsername,  dateTime,  complain,patientUsername);
	}

	public List<Doctor> getDoctorDetails(String doctorUserName) {

		List<Doctor> doctorsDetailsList=doctorDao.getDoctorDetails(doctorUserName);
		
		return doctorsDetailsList;
	}

	

	

	
}