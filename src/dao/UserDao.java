package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("userDao")
public class UserDao {

	@Autowired
	private  SessionFactory sessionFactory;
	
	public  SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	protected  Session session(){
		return sessionFactory.getCurrentSession();
	}

	
}