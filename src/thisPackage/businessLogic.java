package thisPackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.List;
import org.hibernate.mapping.Selectable;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;


public class businessLogic {
	
	SessionFactory factory= new Configuration().configure("/thisPackage/hibernate.cfg.xml").buildSessionFactory();

	public ArrayList<User> getAllNonParticipants(String scheme){
		ArrayList<User> u = new ArrayList<User>();
		
		Session session=factory.openSession();  
		//creating transaction object  
		Transaction t=session.beginTransaction();  
		
		Query query=session.createQuery("select from geocity.user where scheme='"+scheme+"'");
		
		List list=(List) query.list();
		Iterator<Selectable> itr= list.getColumnIterator();
		while(itr.hasNext()){
			u.add((User) itr.next());
		}
		session.close();
		return u;
	}
	
	
}
