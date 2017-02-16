import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.lang.IndexOutOfBoundsException;

public class Network{
	private User currentUser;
	private ArrayList<String> feed = new ArrayList<String>();
	public ArrayList<User> userBase = new ArrayList<User>();


	public void addPost(){
		// Get text
		String content0 = currentUser.writePost();
		// Get a timestamp
		Date timeStamp = new Date();
		SimpleDateFormat form = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formDate = form.format(timeStamp);
		// Both composed
		String content = formDate+" "+currentUser.getName()+" wrote: "+content0;
		feed.add(content);
		System.out.println(" ** POST ADDED ** ");
	}

	public void setCurrentUser(User selection){
		currentUser = selection;
	}

	public void displayFeed(){
		for(String i: feed){
			System.out.println(i);
		}
		
	}

	public void createUser(){
		User newUser = new User();
		String firstNamn;
		String lastNamn;

		// INPUT USER FIRST NAME
		boolean attempt1 = false;
		do {
			try{
				Scanner in = new Scanner(System.in);
				System.out.println("Enter First Name: ");
				firstNamn = in.next();
				System.out.println("Enter Last Name: ");
				lastNamn = in.next();
				newUser.setName(firstNamn, lastNamn);
				attempt1 = true;
			} catch(InputMismatchException e){
				System.out.println("Enter a valid name string.");
			}
		} while(!attempt1);

		// INPUT USER AGE
		boolean attempt3 = false;
		do{
			try{
				Scanner in = new Scanner(System.in);
				System.out.println("Enter Age: ");
				int age = in.nextInt();
				newUser.setAge(age);
				attempt3 = true;
			} catch(InputMismatchException e){
				System.out.println("Enter an integer.");
			}
		} while(!attempt3);
		
		//  SET USER AGE
		userBase.add(newUser);

	} // end create user

	public void mainMenu(){
		int status = 0;
		boolean mainLoop = false;

		do{
			// INSTRUCTION PROMPT
			System.out.println("Select by number:");
			System.out.println("1.) CREATE USER");
			System.out.println("2.) SELECT USER");  
			System.out.println("3.) EXIT");

			// GET USER SELECTION
			boolean done1 = false;
			do{
				try{
					Scanner input = new Scanner(System.in);
					status = input.nextInt();
					done1 = true;
				} 
				catch(InputMismatchException e){
					System.out.println("Please enter a valid integer selection.");
				}
				catch(IndexOutOfBoundsException e){
					System.out.println("Please enter a valid integer selection.");
				}
			} while(!done1);

			// PROCEED WITH SELECTION
			// SELECTION 1
			if(status == 1){
				createUser();
				System.out.println(" ** USER CREATED ** ");
			} 

			// SELECTION 2
			else if(status == 2){
				// DISPLAY USERS AVAILABLE
				System.out.println("Select User by Number: ");
				for(int i=0; i<userBase.size(); i++ ){
					User name = userBase.get(i);
					System.out.println( (i+1)+".) " + name.getName() );
				}

				// SELECT USER AND PROCEED TO SECONDARY MENU OPTIONS
				boolean done2 = false;
				do{
					try
					{
						Scanner input = new Scanner(System.in);
						int selection = input.nextInt();
						if( selection > 0){
							User localUser = userBase.get(selection-1);
							setCurrentUser( localUser );
							System.out.println( "USER SET AS: "+localUser.getName() );
						}
						done2 = true;
					} catch(InputMismatchException e){
						System.out.println("Enter a valid integer selection.");
					} catch(IndexOutOfBoundsException e){
						System.out.println("Enter a valid integer selection.");
					}
				} while(!done2);
				secondMenu();
			} 

			// SELECTION 3
			else if(status == 3){
				mainLoop = true;
			} 

			// SELECTION OTHER
			else{
				System.out.println("Please enter a valid integer selection.");
			}
		} while(!mainLoop);
	} // end mainMenu

	public void secondMenu(){
		int status = 0;
		boolean done = false;
		do{
			System.out.println("Select by number:");
			System.out.println("1.) VIEW FEED");
			System.out.println("2.) WRITE POST");  
			System.out.println("3.) VIEW USER DATA");
			System.out.println("4.) EXIT");
			try{
				Scanner in = new Scanner(System.in);
				status = in.nextInt();

				if(status==1){
					displayFeed();
				} else if(status==2){
					addPost();
				} else if(status==3){
					currentUser.printData();
				} else if(status==4){
					done = true;
				} else{
					System.out.println("Enter a valid integer selection.");
				}
			} catch(InputMismatchException e){
				System.out.println("Enter a valid integer selection.");
			} catch(IndexOutOfBoundsException e){
				System.out.println("Enter a valid integer selection.");
			}

		} while(!done);
	} // end secondMenu

} // end class