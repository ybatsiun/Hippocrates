package dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="calendar")
public class Calendar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public int Id;


	/*@ManyToOne
	@JoinColumn(name="username")*/
	@Autowired
	public Doctor doctor;
	
	
	public Timestamp dateTime ;
	
	public String day;
	
	public boolean isScheduled = false;
	public boolean isBusy = false;

	public String patientFirstName = null;
	public String patientLastName = null;
	public int patientPhoneNumber;
	public String patientEmail = null;
	public String complain = null;
	@Column(name="isScheduled")
	public boolean isScheduled() {
		return isScheduled;
	}
	/*public Calendar(Timestamp dateTime, String day) {
		super();
		this.dateTime = dateTime;
		this.day = day;
	}*/
	public String showDateTimeAndDay() {
		return "Calendar [dateTime=" + dateTime + ", day=" + day + "]";
	}
	public void setScheduled(boolean isScheduled) {
		this.isScheduled = isScheduled;
	}
	@Column(name="isBusy")
	public boolean isBusy() {
		return isBusy;
	}

	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}

	public String getPatientFirstName() {
		return patientFirstName;
	}

	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	public String getPatientLastName() {
		return patientLastName;
	}

	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}

	public int getPatientPhoneNumber() {
		return patientPhoneNumber;
	}

	public void setPatientPhoneNumber(int patientPhoneNumber) {
		this.patientPhoneNumber = patientPhoneNumber;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public String getComplain() {
		return complain;
	}

	public void setComplain(String complain) {
		this.complain = complain;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	@ManyToOne
	@JoinColumn(name="username")
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Timestamp getDateTime() {
		return dateTime ;
	}

	public void setDateTime(Timestamp dateTime ) {
		this.dateTime  = dateTime ;
	}

	

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Calendar(Doctor doctor) {
		super();
		this.doctor = doctor;
	}

	public Calendar() {
		super();
	}
	
}
