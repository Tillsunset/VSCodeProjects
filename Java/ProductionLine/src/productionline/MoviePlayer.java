package productionline;

public class MoviePlayer extends Product implements MultimediaControl{

	Screen screen = new Screen("600x400",40,22);
	MonitorType type = MonitorType.LCD;
	
	public MoviePlayer(String x) {
		super(x);
	}
	
	public String toString() {
		return super.toString() + 
				"\nMonitor Type	 : " + type +
				screen.toString();
	}
	
	@Override
	public void play() {
		System.out.println("Playing");
	
	}

	@Override
	public void stop() {
		System.out.println("Stopping");
	}

	@Override
	public void previous() {
		System.out.println("Previous");
	}

	@Override
	public void next() {
		System.out.println("Next");
	}
	
}
