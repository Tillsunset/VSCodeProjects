package productionline;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ViewFileInfo {

	public static void main(String[] args) throws IOException {
		Path p = Paths.get("c:/LineTests/TestResults.txt");
		
		BufferedReader br = new BufferedReader(new FileReader(p.toString())); 
		
		while (br.readLine() != null) {
			System.out.println(br.readLine());
		}
		br.close();

	}

}
