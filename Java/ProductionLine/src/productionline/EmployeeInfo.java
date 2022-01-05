package productionline;

import java.util.Scanner;

public class EmployeeInfo {
	
	Scanner scan = new Scanner(System.in);
	StringBuilder name = new StringBuilder();
	String code;
	String deptId;
	String p;
	Scanner in;
	
	public EmployeeInfo(){
		setName();
		p = ("[A-Z][a-z]{3}[0-9]{2}");
		setDeptId();
	}
	
	public String reverseString(String id) {
		StringBuilder temp = new StringBuilder();
		System.out.println("here");
		for (int i = id.length(); i > 0; i--) {
			temp.append(id.charAt(i-1));
		}
		return temp.toString();
	}
	
	@Override
	public String toString() {
		return code + "\n" + deptId;
	}
	
	private String getDeptId() {
		in = scan;
		String temp = in.nextLine();
		if (validId(temp)) {
			return reverseString(temp);
		}
		else {
			return "none01";
		}
	}
	
	private void setDeptId() {
		deptId = getDeptId();
	}
	
	public String getId() {
		return deptId;
	}
	
	private boolean validId(String id) {
		return id.matches(p);
	}
	
	public StringBuilder getName() {
		return name;
	}
	
	public String getCode() {
		return code;
	}
	
	private void setName() {
		name.append(inputName());
		if (checkName(name)) {
			createEmployeeCode(name);
		}
		else {
			code = "guest";
		}
	}
	
	private void createEmployeeCode(StringBuilder name) {
		code = name.charAt(0) + name.substring(name.indexOf(" ")+1);
		
	}
	
	private String inputName() {
		return scan.nextLine();
	}
	
	private boolean checkName(StringBuilder name) {

		//System.out.println();
		if (name.indexOf(" ") <= 0) {
			return false;
		}
		else {
			return true;
		}
	}

}
