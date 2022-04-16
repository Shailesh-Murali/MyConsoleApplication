package myConsoleApplication;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import applicationDAL.DALService;
import applicationEntities.Customer;
import applicationEntities.Supplier;


public class MyConsoleApplication 
{
    public static void main( String[] args )
    {
	
    	int choice=0;
    	Scanner sc=null;
    	do {
    		System.out.println("Select any one operation from the below Menu:");
    		System.out.println("---------------------------------------");
    		System.out.println("1 : Add Customer");
    		System.out.println("2 : Add Supplier");
    		System.out.println("3 : Show All Customers");
    		System.out.println("4 : Show All Suppliers");
    		System.out.println("5 : Export a Customer");
    		System.out.println("6 : Export a Supplier");
    		System.out.println("7 : Update a customer detail");
    		System.out.println("8 : Update a supplier detail");
    		System.out.println("9 : Export all customer details");
    		System.out.println("10: Exit");
    		try {
    			sc = new Scanner(System.in);
        		choice= Integer.parseInt(sc.nextLine());
    		}
    		catch(Exception e){
    			System.out.println(e.getMessage());
    			choice=0;
    		}
    		
    		switch(choice)
    		{
    			case 1: System.out.println("Please enter Trading Partner Id,Trading Partner Name,City,Credit Limit,Email Id ");
    					addCustomer(sc);
    					break;
    			case 2: System.out.println("Please enter Trading Partner Id,Trading Partner Name,City,Credit Balance,Pan Number ");
    					addSupplier(sc);
						break;
    			case 3: getAllCustomers();
						break;
    			case 4: getAllSuppliers();
						break;
    			case 5: System.out.println("Please enter Trading Partner Id of Customer");
    					getCustomerById(sc);
						break;
    			case 6: System.out.println("Please enter Trading Partner Id of Supplier");	
    					getSupplierById(sc);
						break;
    			case 7: System.out.println("Please enter Trading Partner Id of Customer");	
						updateCustomerById(sc);
						break;
    			case 8: System.out.println("Please enter Trading Partner Id of Supplier");	
						updateSupplierById(sc);
						break;
    			case 9: exportAllCustomers();
						break;
    			default: break;
    				
    		}

    	
    	} while(choice!=10);
    	sc.close();
    	
    }

	private static void exportAllCustomers() {
		DALService dao= new DALService();
		List<Customer> customers=dao.GetAllCustomers();
		
		XmlMapper xmlMapper= new XmlMapper();
		try {
			xmlMapper.writeValue(new File("D:\\Personal Documents\\Documents\\CitiusTech\\Impact Program\\Projects\\MyConsoleApplication\\Customer\\Customers_List.xml"), customers);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		
		
	}

	private static void updateSupplierById(Scanner sc) {
		int tradePartnerId=Integer.parseInt(sc.nextLine());
		System.out.println("Please enter new Trading Partner Id,Trading Partner Name,City,Credit Balance,PAN ");
		boolean isValid=true;
		int newTradePartnerId=Integer.parseInt(sc.nextLine());
		String tradePartnerName=sc.nextLine();
		String city=sc.nextLine();
		double creditBalance=Double.parseDouble(sc.nextLine());
		String pan=sc.nextLine();
		Supplier supp = new Supplier(newTradePartnerId,tradePartnerName,city,creditBalance,pan);
		//System.out.println(cust);
		String[] response=supp.validate();
		for(String s: response)
		{
			if(s!=null && s.startsWith("Invalid"))
			{
				isValid=false;
				System.out.println(s);
			}
		}
		if(isValid)
		{
			DALService dao= new DALService();
			dao.UpdateSupplierDetails(supp,tradePartnerId);
		}		
	}

	private static void updateCustomerById(Scanner sc) {
		int tradePartnerId=Integer.parseInt(sc.nextLine());
		System.out.println("Please enter Trading Partner Id,Trading Partner Name,City,Credit Limit,Email Id ");
		boolean isValid=true;
		int newTradePartnerId=Integer.parseInt(sc.nextLine());
		String tradePartnerName=sc.nextLine();
		String city=sc.nextLine();
		double creditLimit=Double.parseDouble(sc.nextLine());
		String email=sc.nextLine();
		Customer cust = new Customer(newTradePartnerId,tradePartnerName,city,creditLimit,email);
		String[] response=cust.validate();
		for(String s: response)
		{
			if(s!=null && s.startsWith("Invalid"))
			{
				isValid=false;
				System.out.println(s);
			}
		}
		if(isValid)
		{
			DALService dao= new DALService();
			dao.UpdateCustomerDetails(cust,tradePartnerId);
		}
		
	}

	private static void getSupplierById(Scanner sc) {
		int tradePartnerId=Integer.parseInt(sc.nextLine());
		DALService dao= new DALService();
		Supplier supplier=dao.GetSupplierById(tradePartnerId);
		System.out.println(supplier.toString());
	}

	private static void getCustomerById(Scanner sc) {
		int tradePartnerId=Integer.parseInt(sc.nextLine());
		DALService dao= new DALService();
		Customer customer=dao.GetCustomerById(tradePartnerId);
		System.out.println(customer.toString());

	}

	private static void getAllSuppliers() {
		DALService dao= new DALService();
		List<Supplier> suppliers=dao.GetAllSuppliers();
		System.out.println("----------------------------------------------");
		System.out.println("Below are the list of suppliers:");
		for(Supplier supp: suppliers)
		{
			System.out.println(supp.toString());
		}
		System.out.println("----------------------------------------------");

	}

	private static void getAllCustomers() {
		DALService dao= new DALService();
		List<Customer> customers=dao.GetAllCustomers();
		System.out.println("----------------------------------------------");

		System.out.println("Below are the list of customers:");
		for(Customer cust: customers)
		{
			System.out.println(cust.toString());
		}
		System.out.println("----------------------------------------------");

		
	}

	private static void addSupplier(Scanner sc) {
		boolean isValid=true;
		int tradePartnerId=Integer.parseInt(sc.nextLine());
		String tradePartnerName=sc.nextLine();
		String city=sc.nextLine();
		double creditBalance=Double.parseDouble(sc.nextLine());
		String pan=sc.nextLine();
		Supplier supp = new Supplier(tradePartnerId,tradePartnerName,city,creditBalance,pan);
		//System.out.println(cust);
		String[] response=supp.validate();
		for(String s: response)
		{
			if(s!=null && s.startsWith("Invalid"))
			{
				isValid=false;
				System.out.println(s);
			}
		}
		if(isValid)
		{
			DALService dao= new DALService();
			dao.SaveSupplierDetails(supp);
		}
	}

	private static void addCustomer(Scanner sc) {
		boolean isValid=true;
		int tradePartnerId=Integer.parseInt(sc.nextLine());
		String tradePartnerName=sc.nextLine();
		String city=sc.nextLine();
		double creditLimit=Double.parseDouble(sc.nextLine());
		String email=sc.nextLine();
		Customer cust = new Customer(tradePartnerId,tradePartnerName,city,creditLimit,email);
		//System.out.println(cust);
		String[] response=cust.validate();
		for(String s: response)
		{
			if(s!=null && s.startsWith("Invalid"))
			{
				isValid=false;
				System.out.println(s);
			}
		}
		if(isValid)
		{
			DALService dao= new DALService();
			dao.SaveCustomerDetails(cust);
		}
		
	}
}
