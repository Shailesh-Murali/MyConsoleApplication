package applicationDAL;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import applicationEntities.Customer;
import applicationEntities.Supplier;

public class DALService {
	 
	public void SaveCustomerDetails(Customer cust) {
		
		Configuration config = new Configuration().configure().addAnnotatedClass(Customer.class); 
		 ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		 SessionFactory sf= config.buildSessionFactory(reg);
		 Session session = sf.openSession(); 
		 Transaction tx = session.beginTransaction(); 
		 session.save(cust); 
		 //b=(Alien)session.get(Alien.class, 2); 
		 tx.commit();


	}

	public void SaveSupplierDetails(Supplier supp) {

		Configuration config = new Configuration().configure().addAnnotatedClass(Supplier.class); 
		 ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		 SessionFactory sf= config.buildSessionFactory(reg);
		 Session session = sf.openSession(); 
		 Transaction tx = session.beginTransaction(); 
		 session.save(supp); 
		 //b=(Alien)session.get(Alien.class, 2); 
		 tx.commit();
	}

	public List<Customer> GetAllCustomers() {
		Configuration config = new Configuration().configure().addAnnotatedClass(Customer.class); 
		 ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		 SessionFactory sf= config.buildSessionFactory(reg);
		 Session session = sf.openSession(); 
		 Transaction tx = session.beginTransaction(); 
		 Query query= session.createQuery("from Customer");
		 List<Customer> customers= query.list();      
		 tx.commit();
		 return customers;
	}

	public List<Supplier> GetAllSuppliers() {

		Configuration config = new Configuration().configure().addAnnotatedClass(Supplier.class); 
		 ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		 SessionFactory sf= config.buildSessionFactory(reg);
		 Session session = sf.openSession(); 
		 Transaction tx = session.beginTransaction(); 
		 Query query= session.createQuery("from Supplier");
		 List<Supplier> suppliers= query.list();      
		 tx.commit();
		 return suppliers;
	}

	public Customer GetCustomerById(int customerid) {


		Configuration config = new Configuration().configure().addAnnotatedClass(Customer.class); 
		 ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		 SessionFactory sf= config.buildSessionFactory(reg);
		 Session session = sf.openSession(); 
		 Transaction tx = session.beginTransaction(); 
		 Customer customer=(Customer)session.get(Customer.class, customerid); 
		 tx.commit();
		 customer.SaveToFile("D:\\Personal Documents\\Documents\\CitiusTech\\Impact Program\\Projects\\MyConsoleApplication\\Customer\\"+customerid);
		 return customer;
	}

	public Supplier GetSupplierById(int supplierid) {

		Configuration config = new Configuration().configure().addAnnotatedClass(Supplier.class); 
		 ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		 SessionFactory sf= config.buildSessionFactory(reg);
		 Session session = sf.openSession(); 
		 Transaction tx = session.beginTransaction(); 
		 Supplier supplier=(Supplier)session.get(Supplier.class, supplierid); 
		 tx.commit();
		 supplier.SaveToFile("D:\\Personal Documents\\Documents\\CitiusTech\\Impact Program\\Projects\\MyConsoleApplication\\Supplier\\"+supplierid);
		 return supplier;
	}

	public void UpdateCustomerDetails(Customer cust, int tradePartnerId) {
		 Configuration config = new Configuration().configure().addAnnotatedClass(Customer.class); 
		 ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		 SessionFactory sf= config.buildSessionFactory(reg);
		 Session session = sf.openSession(); 
		 Transaction tx = session.beginTransaction(); 
		 Query query= session.createQuery("update Customer set tradingPartnerId=:ntpid, tradingPartnerName=:tpname, city=:city,creditLimit=:cl, emailid=:e where tradingPartnerId=:tpid");
		 query.setParameter("ntpid", cust.getTradingPartnerId());
		 query.setParameter("tpname", cust.getTradingPartnerName());
		 query.setParameter("city", cust.getCity());
		 query.setParameter("cl", cust.getCreditLimit());
		 query.setParameter("e", cust.getEmailid());
		 query.setParameter("tpid", tradePartnerId);
		 int status= query.executeUpdate();
		 System.out.println(status);
		 tx.commit();
	}

	public void UpdateSupplierDetails(Supplier supp, int tradePartnerId) {
		 Configuration config = new Configuration().configure().addAnnotatedClass(Supplier.class); 
		 ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		 SessionFactory sf= config.buildSessionFactory(reg);
		 Session session = sf.openSession(); 
		 Transaction tx = session.beginTransaction(); 
		 Query query= session.createQuery("update Supplier set tradingPartnerId=:ntpid, tradingPartnerName=:tpname, city=:city,creditBalance=:cb, panNo=:p where tradingPartnerId=:tpid");
		 query.setParameter("ntpid", supp.getTradingPartnerId());
		 query.setParameter("tpname", supp.getTradingPartnerName());
		 query.setParameter("city", supp.getCity());
		 query.setParameter("cb", supp.getCreditBalance());
		 query.setParameter("p", supp.getPanNo());
		 query.setParameter("tpid", tradePartnerId);
		 int status= query.executeUpdate();
		 System.out.println(status);
		 tx.commit();
	}
}
