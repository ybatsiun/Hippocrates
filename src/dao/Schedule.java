package dao;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class Schedule implements Serializable {


	  public LocalDateTime[] appointmentTime= new LocalDateTime[6] ;
	
	public ArrayList  <LocalDateTime[]> schedule = new ArrayList <LocalDateTime[]> ();
	
	private static final long serialVersionUID = 1L;

	public ArrayList<LocalDateTime[]> getShedule() {
		return schedule;
	}

	public void setShedule(ArrayList<LocalDateTime[]> schedule) {
		this.schedule = schedule;
	}
	
	
	
	
}
