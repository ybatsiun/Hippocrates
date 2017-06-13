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


	@ManyToOne
	@JoinColumn(name="username")
	@Autowired
	public Doctor doctor;
	
	
	public Timestamp dateTime ;
	
	public String day;
	
	@Column(name="isScheduled")
	public boolean scheduled ;
	public boolean busy = false;

	public String patientUserName = null;
	public String patientFirstName = null;
	public String patientLastName = null;
	public String patientEmail = null;
	public String patientPhone = null;
	public String complain = null;
	
	
	
	public boolean isScheduled() {
		return scheduled;
	}

	

	public void setScheduled(boolean scheduled) {
		this.scheduled = scheduled;
	}



	public String showDateTimeAndDay() {
		return "Calendar [dateTime=" + dateTime + ", day=" + day + "]";
	}
	
	

	public boolean isBusy() {
		return busy;
	}



	public void setBusy(boolean busy) {
		this.busy = busy;
	}



	

	public String getPatientUserName() {
		return patientUserName;
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



	public String getPatientEmail() {
		return patientEmail;
	}



	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}



	public String getPatientPhone() {
		return patientPhone;
	}



	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}



	public void setPatientUserName(String patientUserName) {
		this.patientUserName = patientUserName;
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



	public Calendar(Doctor doctor, Timestamp dateTime, String day, boolean scheduled, boolean busy,
			String patientUserName, String patientFirstName, String patientLastName, String patientEmail,
			String patientPhone, String complain) {
		super();
		this.doctor = doctor;
		this.dateTime = dateTime;
		this.day = day;
		this.scheduled = scheduled;
		this.busy = busy;
		this.patientUserName = patientUserName;
		this.patientFirstName = patientFirstName;
		this.patientLastName = patientLastName;
		this.patientEmail = patientEmail;
		this.patientPhone = patientPhone;
		this.complain = complain;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (busy ? 1231 : 1237);
		result = prime * result + ((complain == null) ? 0 : complain.hashCode());
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((doctor == null) ? 0 : doctor.hashCode());
		result = prime * result + ((patientEmail == null) ? 0 : patientEmail.hashCode());
		result = prime * result + ((patientFirstName == null) ? 0 : patientFirstName.hashCode());
		result = prime * result + ((patientLastName == null) ? 0 : patientLastName.hashCode());
		result = prime * result + ((patientPhone == null) ? 0 : patientPhone.hashCode());
		result = prime * result + ((patientUserName == null) ? 0 : patientUserName.hashCode());
		result = prime * result + (scheduled ? 1231 : 1237);
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Calendar other = (Calendar) obj;
		if (busy != other.busy)
			return false;
		if (complain == null) {
			if (other.complain != null)
				return false;
		} else if (!complain.equals(other.complain))
			return false;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (doctor == null) {
			if (other.doctor != null)
				return false;
		} else if (!doctor.equals(other.doctor))
			return false;
		if (patientEmail == null) {
			if (other.patientEmail != null)
				return false;
		} else if (!patientEmail.equals(other.patientEmail))
			return false;
		if (patientFirstName == null) {
			if (other.patientFirstName != null)
				return false;
		} else if (!patientFirstName.equals(other.patientFirstName))
			return false;
		if (patientLastName == null) {
			if (other.patientLastName != null)
				return false;
		} else if (!patientLastName.equals(other.patientLastName))
			return false;
		if (patientPhone == null) {
			if (other.patientPhone != null)
				return false;
		} else if (!patientPhone.equals(other.patientPhone))
			return false;
		if (patientUserName == null) {
			if (other.patientUserName != null)
				return false;
		} else if (!patientUserName.equals(other.patientUserName))
			return false;
		if (scheduled != other.scheduled)
			return false;
		return true;
	}
	
	
	
}