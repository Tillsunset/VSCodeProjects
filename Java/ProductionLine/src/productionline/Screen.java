package productionline;

public class Screen implements ScreenSpec {
	String resolution;
	int refreshrate;
	int responsetime;
	
	public Screen (String x, int y, int z) {
		resolution = x;
		refreshrate = y;
		responsetime = z;
	}
	
	public String toString() {
		return "\nResolution    	 : " + resolution +
			   "\nRefresh Rate  	 : " + refreshrate +
			   "\nResponse Time 	 : " + responsetime;
	}

	public String getResolution() {
		return resolution;
	}

	public int getRefreshRate() {
		return refreshrate;
	}

	public int getResponseTime() {
		return responsetime;
	}

}
