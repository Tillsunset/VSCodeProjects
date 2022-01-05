package productionline;

import java.util.Date;

public abstract class Product implements Item, Comparable<Product>{
	int serialNumber;
	//String manufacturer;
	String manufacturedOn;
	String name;
	static int currentProductionNumber = 0;
	Date date = new Date();
	
	public Product(String x) {
		name = x;
		serialNumber = currentProductionNumber;
		currentProductionNumber++;
		manufacturedOn = date.toString();
	}
	
	public String toString() {
		return  "Manufacturer	 : " + manufacturer +
				"\nSerial Number    : " + serialNumber +
				"\nDate		 : " + manufacturedOn +
				"\nName		 : " + name;
	}
	
	public int compareTo(Product x) {
		return this.getName().compareTo(x.getName());
	}
	
	public void setProductionNumber(int x) {
		currentProductionNumber = x;
	}
	
	public void setName(String x) {
		name = x;
	}
	
	public String getName() {
		return name;
	}
	
	public String getManfactureDate() {
		return manufacturedOn;
	}
	
	public int getSerialNumber() {
		return serialNumber;
	}
}
