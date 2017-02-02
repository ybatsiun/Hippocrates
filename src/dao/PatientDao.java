package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
		return session().createQuery("from Patient").list() ;
		
	}
	
	public void createPatient(Patient patient){
		session().save(patient);
		System.out.println("Patient registered");
	}
	
	@SuppressWarnings("unchecked")
	public List <Patient> getPatientByUsername(String username){
		Criteria crit = session().createCriteria(Patient.class);
		crit.add(Restrictions.eq("username",username));
		
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Patient> showDoctorsForAdmin() {
       Criteria crit = session().createCriteria(Patient.class);
		
		return crit.list();
		
	}
	
}
