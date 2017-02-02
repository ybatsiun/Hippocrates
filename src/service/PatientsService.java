package service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

public void createPatient(Patient patient){
	patientDao.createPatient(patient);
}

public List<Patient> showPatientsListForAdmin() {
	List<Patient> patientsListForAdmin = patientDao.showDoctorsForAdmin();
	return patientsListForAdmin;
}


}
