package applicationEntities;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.regex.Pattern;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="customer_table")

public class Customer extends TradingPartner implements Serializable{
	
	double creditLimit;
	String emailid;
	
	public Customer(){
		super();
	}
	
	public double getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public Customer(int tradingPartnerId, String tradingPartnerName, String city,double creditLimit, String emailid) {
		super(tradingPartnerId,tradingPartnerName,city);
		this.creditLimit = creditLimit;
		this.emailid = emailid;
	}
	@Override
	public String[] validate() {
		
		String[] response = new String [5];
		int i=0;
		if(this.tradingPartnerId<=0)
		{
			response[i]="Invalid TradingPartnerId";
			i++;
		}
		if("".equalsIgnoreCase(this.tradingPartnerName) || this.tradingPartnerName==null || this.tradingPartnerName.length()<5)
		{
			response[i]="Invalid TradingPartnerName";
			i++;
		}
		
		if("".equalsIgnoreCase(this.city) || this.city==null || this.city.length()<3)
		{
			response[i]="Invalid City";
			i++;
		}
		
		if(this.creditLimit>50000)
		{
			response[i]="Invalid CreditLimit";
			i++;
		}
		if("".equalsIgnoreCase(this.emailid) || this.emailid==null || !Pattern.compile("^(.+)@(.+)$").matcher(this.emailid).matches())
		{
			response[i]="Invalid Email";
			i++;
		}
		return response;
	}
	@Override
	public void SaveToFile(String filepath) {
		ObjectOutputStream oos=null;
		try {
			oos= new ObjectOutputStream(new FileOutputStream(filepath+".txt"));
			oos.writeObject(this);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	@Override
	public String toString() {
		return "Customer [creditLimit=" + creditLimit + ", emailid=" + emailid + ", tradingPartnerId="
				+ tradingPartnerId + ", tradingPartnerName=" + tradingPartnerName + ", city=" + city + "]";
	}
	
	
	

}
