package thisPackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.List;
import org.hibernate.mapping.Selectable;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

public class DAO {
	public static void main(String[] args){
		SessionFactory factory= new Configuration().configure("/thisPackage/hibernate.cfg.xml").buildSessionFactory();
		Session session=factory.openSession();  
		//creating transaction object  
		Transaction t=session.beginTransaction();  
		
		Query query=session.createQuery("select from geocity.user");
		query.setFirstResult(5);
		query.setMaxResults(10);
		List list=(List) query.list();//will return the records from 5 to 10th number
		Iterator<Selectable> itr= list.getColumnIterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
		}
	}
}
