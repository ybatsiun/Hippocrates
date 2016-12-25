package service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


}
