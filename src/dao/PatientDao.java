package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("patientDao")
public class PatientDao extends UserDao implements Serializable {

	private static final long serialVersionUID = 1L;
/*Returns all patients which are in DB*/
	@SuppressWarnings("unchecked")
	public List<Patient> getAllPatients() {
		System.out.println("PatientDao.getAllPatients started");
		Criteria crit = session().createCriteria(Patient.class);
		System.out.println("PatientDao.getAllPatients finished");
		return crit.list();

	}
/*Creates a patient*/
	public void createPatient(Patient patient) {
		
		session().save(patient);
		
		
		System.out.println("Patient registered");
	}
/*Returns a list of patients ( one patient in the list) with a username defined in the method parameters.*/
	@SuppressWarnings("unchecked")
	public List<Patient> getPatientByUsername(String username) {
		Criteria crit = session().createCriteria(Patient.class);
		crit.add(Restrictions.eq("username", username));

		return crit.list();
	}

	
/*Returns a list of doctors with enabled=1 with an info for a patient.*/
	@SuppressWarnings("unchecked")
	public List<Doctor> showDoctorsForPatient() {
		Criteria crit = session().createCriteria(Doctor.class);

		crit.add(Restrictions.eq("enabled", 1))
				.setProjection(Projections.projectionList().add(Projections.property("lastName"), "lastName")
						.add(Projections.property("firstName"), "firstName").add(Projections.property("field"), "field")
						.add(Projections.property("email"), "email").add(Projections.property("username"), "username"))
				.setResultTransformer(Transformers.aliasToBean(Doctor.class));

		return crit.list();

	}
/*Finds all appointments booked by a current user*/
	@SuppressWarnings("unchecked")
	public List<Calendar> getAppointmentsTable(String patientUserName) {
		
		Query crit = session().createSQLQuery("select dateTime,id,field,firstName,lastName from doctors  "
				+ "left outer join calendar on doctors.username = calendar.username "
				+ "where patientUserName='"+ patientUserName+"'");

		
		
		
		return crit.list();
	}
/*Cancels an appointment with an id*/
	public void cancelAnAppointment(int id) {
		Query crit = session().createSQLQuery(" update calendar set busy=false,patientFirstName=null,patientLastName=null,"
				+ "patientUserName=null,patientPhone=null,patientEmail=null,complain=null where id= '"+id+"'"
				);

		crit.executeUpdate();
	}

	
}
