package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import dao.Patient;




@Repository
@Transactional
@Component("patientDao")
public class PatientDao extends UserDao implements Serializable  {

	
	
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public  List<Patient> getAllPatients (){
		//тут надо написать JOIN. соеденить patients с users,когда в users authority=ROLE_PATIENT
		return session().createQuery("from Patient").list() ;
		
	}
	
	public void createPatient(Patient patient){
		session().save(patient);
		System.out.println("Patient registered");
	}
	
}
