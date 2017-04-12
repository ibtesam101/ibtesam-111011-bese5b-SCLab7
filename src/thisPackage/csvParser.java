package thisPackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;  
import org.hibernate.service.ServiceRegistry;

public class csvParser {
	
	public static void main(String[] args){
		SessionFactory factory= new Configuration().configure("/thisPackage/hibernate.cfg.xml").buildSessionFactory();
		String csvFile = "test_two-anon.csv";
		String line = "";
		String cvsSplitBy = ",";
	
		int count = 0;
		
		BufferedReader br = null;
		
		String check = "wut";
		
		try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

            	if(count>3){
            		
            		Session session=factory.openSession();  
        			//creating transaction object  
            		Transaction t=session.beginTransaction();  

        		
	                // use comma as separator
	                String[] city = line.split(cvsSplitBy);
	
	                User c = new User();
	                
	                System.out.println("user [id= " + city[0] + " , scheme="+city[2]+ "]"+
	                		"[time= " + city[3] + " , state="+city[4]+ "]"+
	                		"[tc1= " + city[6] + " , sc1="+city[7]+ "]"+
	                		"[statec2= " + city[9] + " , tc3="+city[10]+ "]"+
	                		"[statec3= " + city[12] + " , tc4="+city[13]+ "]"+
	                		"[statec4= " + city[15] + " , tc5="+city[16]+ "]"+
	                		"[statec5= " + city[18] + " , tc6="+city[19]+ "]"+
	                		"[statec6= " + city[21] + " , tc7="+city[22]+ "]"+
	                		"[statec7= " + city[24]+ " , tc7="+city[25]+ "]");
	                c.setId(count);
	                c.setScheme(city[2]);
	                try{
	                	c.setTimeTaken(Float.parseFloat(city[3]));
	                }
	                catch(NumberFormatException e){
	                	
	                }
	                
	                c.setState(Boolean.parseBoolean(city[4]));
	                
	                try{
	                	c.setTimeC1(Float.parseFloat(city[6]));
	                }
	                catch(NumberFormatException e){
	                	
	                }
	                c.setStateC1(Boolean.parseBoolean(city[7]));
	                
	                
	                session.persist(c);
	                t.commit();

	        		session.close();
            	}
            	
            	count++;
                
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		
	}
}
