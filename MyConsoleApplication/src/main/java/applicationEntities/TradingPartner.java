package applicationEntities;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public  abstract class TradingPartner {
	
	@Id
	int tradingPartnerId;
	String tradingPartnerName;
	String city;
	
	public TradingPartner() {
		
	}
	public int getTradingPartnerId() {
		return tradingPartnerId;
	}

	public void setTradingPartnerId(int tradingPartnerId) {
		this.tradingPartnerId = tradingPartnerId;
	}

	public String getTradingPartnerName() {
		return tradingPartnerName;
	}

	public void setTradingPartnerName(String tradingPartnerName) {
		this.tradingPartnerName = tradingPartnerName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public TradingPartner(int tradingPartnerId, String tradingPartnerName, String city) {
		this.tradingPartnerId = tradingPartnerId;
		this.tradingPartnerName = tradingPartnerName;
		this.city = city;
	}

	public String[] validate() {
		
		String[] response = new String[3];
		return response;
		
	}
	
	public abstract void SaveToFile(String filepath);


}
