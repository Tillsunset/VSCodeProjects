import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) throws Exception{
		File file = new File("C:\\1-1000.txt");  //TODO find actual file
		
		ArrayList<String> test = new ArrayList<>();
		BufferedReader in = new BufferedReader(new FileReader(file));
		String temp;
		
		while ((temp = in.readLine()) != null){
			test.add(temp);
		}
		
		System.out.println(sort(test));
		in.close();
	}
	
	public static ArrayList<String> sort(ArrayList<String> x) {
		ArrayList<String> lower =  new ArrayList<>();
		ArrayList<String> same =  new ArrayList<>();
		ArrayList<String> upper =  new ArrayList<>();
		String pivot = x.get(0);
		
		for (String a: x) {
			if (pivot.compareToIgnoreCase(a) > 0) {
				lower.add(a);
			}
			else if (pivot.compareToIgnoreCase(a) < 0) {
				upper.add(a);
			}
			else {
				same.add(a);
			}
		}

		if (lower.size() > 0) {
			lower = sort(lower);
		}if (upper.size() > 0) {
			upper = sort(upper);
		}
		
		lower.addAll(same);
		lower.addAll(upper);
		return lower;
	}
}
