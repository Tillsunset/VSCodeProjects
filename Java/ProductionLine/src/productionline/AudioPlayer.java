package productionline;

public class AudioPlayer extends Product implements MultimediaControl{
	
	String audioSpecification;
	ItemType mediaType;
	
	AudioPlayer(String name, ItemType x) {
		super(name);
		mediaType = x;
		
		switch(x) {
		case AU:
			audioSpecification = "Audio";
			break;
		case VI:
			audioSpecification = "Visual";
			break;
		case AM:
			audioSpecification = "AudioMobile";
			break;
		case VM:
			audioSpecification = "VisualMobile";
			break;
		}
	}
	
	@Override
	public String toString() {
		return super.toString() +
				"\nAudio Spec	 : " + audioSpecification +
				"\nType		 : " + mediaType;
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
