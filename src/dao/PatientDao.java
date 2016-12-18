package dao;

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
public class PatientDao extends ParentDao  {

	
	
	@SuppressWarnings("unchecked")
	public  List<Patient> getAllPatients (){
		//тут надо написать JOIN. соеденить patients с users,когда в users authority=ROLE_PATIENT
		return session().createQuery("from Patient").list() ;
		
	}
	
}
