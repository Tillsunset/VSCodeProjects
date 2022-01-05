package productionline;

public interface Item {
	
	final String manufacturer = "OracleProduction";
	
	void setProductionNumber(int x);
	void setName(String x);
	String getName();
	String getManfactureDate();
	int getSerialNumber();

}
