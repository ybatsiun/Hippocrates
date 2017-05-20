package service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.Calendar;
import dao.Doctor;
import dao.Patient;
import dao.PatientDao;
import dao.User;

@Transactional
@Service("PatientsService")
public class PatientsService implements Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
	public PatientDao patientDao;

	@Autowired
	public void setPatientDao(PatientDao patientDao) {
		this.patientDao = patientDao;
	}

	public void createPatient(Patient patient) {
		patientDao.createPatient(patient);
	}

	public List<Patient> showPatientsListForAdmin() {
		List<Patient> patientsListForAdmin = patientDao.showDoctorsForAdmin();
		return patientsListForAdmin;
	}

	public List<Doctor> showDoctorsForPatient() {
		List<Doctor> patientsListForAdmin = patientDao.showDoctorsForPatient();
		return patientsListForAdmin;
	}

	public List<Calendar> getAppointmentsTable(String patientUserName) {
		List<Calendar> appointmentsTable = patientDao.getAppointmentsTable(patientUserName);
		return appointmentsTable;
	}

	public void cancelAnAppointment(int id) {
		patientDao.cancelAnAppointment(id);
	}
}
