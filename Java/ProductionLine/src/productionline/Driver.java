package productionline;

import java.io.IOException;
import java.util.ArrayList;
//import java.util.Collections;

public class Driver {

	public static void main(String[] args) {

		/*AudioPlayer test2 = new AudioPlayer("test", ItemType.AU);
		AudioPlayer test = new AudioPlayer("test", ItemType.AU);
		System.out.println(test.toString());
		
		test.play();*/
		
		/*Screen test = new Screen("600x400",40,22);
		System.out.println(test.toString());*/
		
		/*MoviePlayer test = new MoviePlayer("MoviePlayer");
		System.out.println(test.toString());
		test.stop();*/
		
		//MoviePlayer test = new MoviePlayer("MoviePlayer");
		//AudioPlayer test2 = new AudioPlayer("test", ItemType.AU);
		//test.play();
		//test2.play();
		

		/*ArrayList<Product> testArray = new ArrayList<>();
		
		int quota = 5;
		for(int i = 0; i < quota; i++) {
			MoviePlayer test = new MoviePlayer("MoviePlayer");
			AudioPlayer test2 = new AudioPlayer("test", ItemType.AU);
			testArray.add(test);
			testArray.add(test2);
		}
		
		Collections.sort(testArray);
		
		print(testArray);*/
		
		
		EmployeeInfo test = new EmployeeInfo();
		
		ProcessFiles tester = new ProcessFiles();
		tester.CreateDirectory();
		try {
			tester.WriteFile(test);
			tester.Writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void print(ArrayList<Product> x) {
		for( Product p : x) {
			System.out.println(p.getName());
		}
	}
}
