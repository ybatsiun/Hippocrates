package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import dao.Patient;

@Repository
@Transactional
@Component("patientDao")
public class PatientDao extends UserDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<Patient> getAllPatients() {
		return session().createQuery("from Patient").list();

	}

	public void createPatient(Patient patient) {
		session().save(patient);
		System.out.println("Patient registered");
	}

	@SuppressWarnings("unchecked")
	public List<Patient> getPatientByUsername(String username) {
		Criteria crit = session().createCriteria(Patient.class);
		crit.add(Restrictions.eq("username", username));

		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Patient> showDoctorsForAdmin() {
		Criteria crit = session().createCriteria(Patient.class);

		return crit.list();

	}

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

	@SuppressWarnings("unchecked")
	public List<Calendar> getAppointmentsTable(String patientUserName) {
		
		Query crit = session().createSQLQuery("select dateTime,id,field,firstName,lastName from doctors  "
				+ "left outer join calendar on doctors.username = calendar.username "
				+ "where patientUserName='"+ patientUserName+"'");

		
		
		
		return crit.list();
	}

	public void cancelAnAppointment(int id) {
		Query crit = session().createSQLQuery(" update calendar set busy=false,patientFirstName=null,patientLastName=null,"
				+ "patientUserName=null,patientPhone=null,patientEmail=null,complain=null where id= '"+id+"'"
				);

		crit.executeUpdate();
	}

	
}
