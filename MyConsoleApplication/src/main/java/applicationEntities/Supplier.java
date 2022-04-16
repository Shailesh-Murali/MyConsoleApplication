package applicationEntities;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Entity
@Table(name = "supplier_table")

public class Supplier extends TradingPartner {

	double creditBalance;
	String panNo;

	public Supplier() {
		super();
	}

	public double getCreditBalance() {
		return creditBalance;
	}

	public void setCreditBalance(double creditBalance) {
		this.creditBalance = creditBalance;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public Supplier(int tradingPartnerId, String tradingPartnerName, String city, double creditBalance, String panNo) {
		super(tradingPartnerId, tradingPartnerName, city);
		this.creditBalance = creditBalance;
		this.panNo = panNo;
	}

	@Override
	public String[] validate() {

		String[] response = new String[5];
		int i = 0;
		if (this.tradingPartnerId <= 0) {
			response[i] = "Invalid TradingPartnerId";
			i++;
		}
		if ("".equalsIgnoreCase(this.tradingPartnerName) || this.tradingPartnerName == null
				|| this.tradingPartnerName.length() < 5) {
			response[i] = "Invalid TradingPartnerName";
			i++;
		}

		if ("".equalsIgnoreCase(this.city) || this.city == null || this.city.length() < 3) {
			response[i] = "Invalid City";
			i++;
		}

		if (this.creditBalance > 150000) {
			response[i] = "Invalid CreditBalance";
			i++;
		}
		if ("".equalsIgnoreCase(this.panNo) || this.panNo == null
				|| !Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}").matcher(this.panNo).matches()) {
			response[i] = "Invalid PAN";
			i++;
		}
		return response;
	}

	@Override
	public void SaveToFile(String filepath) {
		XmlMapper xmlMap = new XmlMapper();
		try {
			xmlMap.writeValue(new File(filepath + ".xml"), this);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public String toString() {
		return "Supplier [creditBalance=" + creditBalance + ", panNo=" + panNo + ", tradingPartnerId="
				+ tradingPartnerId + ", tradingPartnerName=" + tradingPartnerName + ", city=" + city + "]";
	}

	
}
