package productionline;

import java.io.IOException;
import java.nio.file.Files;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ProcessFiles {
	private Path p = Paths.get("c:/LineTests/");
	//private Path p2 = Paths.get("TestResults.txt");
	private Path p3 = Paths.get("c:/LineTests/TestResults.txt");
	FileWriter Writer;
	
	public void CreateDirectory() {
		if (Files.notExists(p)) {
			try {
				Files.createDirectory(p);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		
		try {
			Writer = new FileWriter(p3.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void WriteFile(EmployeeInfo emp) throws IOException{
		Writer.write(emp.toString());

		
	}
	
	public void WriteFile(ArrayList<Product> products) throws IOException{
		Writer.write(products.toString());
	}
}
