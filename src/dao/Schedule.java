package dao;

import java.io.Serializable;
import java.time.LocalTime;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class Schedule implements Serializable {
	
	public LocalTime monday ;

	public LocalTime getMonday() {
		return monday;
	}

	public void setMonday(LocalTime monday) {
		this.monday = monday;
	}



/*public HashMap <String,String> schedule=new HashMap <String,String>();
	  public LocalDateTime[] appointmentTime= new LocalDateTime[6] ;
	
	public ArrayList  <LocalDateTime[]> schedule = new ArrayList <LocalDateTime[]> ();
	

LocalTime[][] time = new LocalTime[5][];
	public LocalTime[][] getTime() {
	return time;
}

public void setTime(LocalTime[][] time) {
	this.time = time;
}

	private static final long serialVersionUID = 1L;

	public HashMap<String, String> getSchedule() {
		
		schedule.put(key, value)
		return schedule;
	}

	public void setSchedule(HashMap<String, String> schedule) {
		this.schedule = schedule;
	}

	public ArrayList<LocalDateTime[]> getShedule() {
		return schedule;
		
	}

	public void setShedule(ArrayList<LocalDateTime[]> schedule) {
		this.schedule = schedule;
	}
	*/
	
	
	
}
