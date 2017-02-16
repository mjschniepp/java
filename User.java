import java.util.Scanner;

public class User{
	private String firstName;
	private String lastName;
	private int age;
	private Scanner input = new Scanner(System.in);

	public void setName(String userFName, String userLName){
		firstName = userFName;
		lastName = userLName;
	}

	public void setAge(int userAge){
		age = userAge;
	}

	public String getName(){
		String fullName = firstName+" "+lastName;
		return fullName;
	}

	public void printData(){
		System.out.println("USER DATA");
		System.out.println("-----------");
		System.out.println("NAME: "+firstName+" "+lastName);
		System.out.println("AGE: "+age);
		System.out.println("-----------");
	}

	public String writePost(){
		System.out.println("Write a post:");   
		String content = input.nextLine();
		return content;
	}
}