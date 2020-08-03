package as3;



import java.io.IOException;
import java.util.Scanner;




public class UserApplication1 {
	static Scanner scan = new Scanner(System.in);
	static boolean validLogins = false;
	static String username = null;
	
	
	public static void main(String[] args) throws IOException {
		
		loginScreen();
		
	}
	
	public static void loginScreen() throws IOException {
		UserService.readFile();

		int invalidLogins = 0;
		int indexOfUsers = 0;
		
	while (invalidLogins < 5 && !validLogins) {
		System.out.println("Enter your email:");
		String email = scan.nextLine();
		System.out.println("Enter your password:");
		String password = scan.nextLine();
		
		for(indexOfUsers = 0; indexOfUsers < 20; indexOfUsers++) {
			if((email.equalsIgnoreCase(UserService.users[indexOfUsers].getEmail())) &&  (password.equals(UserService.users[indexOfUsers].getPassword()))){
			
			System.out.println("Welcome " + UserService.users[indexOfUsers].getName() +", " +UserService.users[indexOfUsers].getRole());
			username = UserService.users[indexOfUsers].getName();
			
			validLogins = true;
			
				if (UserService.users[indexOfUsers].getRole().equals("super_user")){
			superUserMenu(indexOfUsers);}
				else {
			normalUserMenu(indexOfUsers);}
				}	
		}
		
		if (validLogins == false){
			System.out.println("Invalid login. Please try again");
			invalidLogins++;
		}
		if(invalidLogins == 5) {
			System.out.println("Too many failed login attempts, you are now locked out.");
		}
	}
	 
	System.out.println("Program terminates");
	scan.close();
	}

	

	private static void superUserMenu(int indexOfUsers) throws IOException {

		System.out.println("----------");
		while(validLogins == true) {
		System.out.println("Please choose from the following options:");
			System.out.println("(0) Log in as another user");
			System.out.println("(1) Update username");
			System.out.println("(2) Update password");
			System.out.println("(3) Update name");
			System.out.println("(4) Exit");
            
            
			String option = scan.nextLine();
			if(option.equals("0")) {
				while (validLogins) {
			System.out.println("Which user would you like to login as? (Type in a valid user email)");
			String email = scan.nextLine();
			
				for(indexOfUsers = 0; indexOfUsers < 20; indexOfUsers++) {
					if((email.equalsIgnoreCase(UserService.users[indexOfUsers].getEmail()))){
				
						System.out.println("Welcome " + UserService.users[indexOfUsers].getName() +", " +UserService.users[indexOfUsers].getRole());
						 username = UserService.users[indexOfUsers].getName();
						validLogins = true;
						normalUserMenu(indexOfUsers);
					}
				}
				}	
			}
			if(option.equals("1")) {
				System.out.println("Updating username");
				UserService.changeUserEmail(scan);
				System.out.println("Your username has been changed");
				}
			if(option.equals("2")) {
				System.out.println("Updating password");
				UserService.changeUserPassword(scan);
				System.out.println("Your password has been changed");
				}
			if(option.equals("3")) {
				System.out.println("Updating name");
				UserService.changeUserName(scan);
				System.out.println("Your name has been changed, "+ UserService.users[indexOfUsers].getName());
				}	
			if(!option.equals("4")) {
				System.out.println("Wrong input.");
				}
			}	
			}
		
	
	private static void normalUserMenu(int indexOfUsers) throws IOException {
		if (UserService.users[indexOfUsers].getRole().equals("normal_user")){
			while(validLogins == true) {
			System.out.println("(1) Update username");
			System.out.println("(2) Update password");
			System.out.println("(3) Update name");
			System.out.println("(4) Exit");
			
			
			String option = scan.nextLine();
			if(option.equals("1")) {
				System.out.println("Updating username");
				UserService.changeUserEmail(scan);
				System.out.println("Your username has been changed");
				break;
				}
			if(option.equals("2")) {
				System.out.println("Updating password");
				UserService.changeUserPassword(scan);
				System.out.println("Your password has been changed");
				break;
				}
			if(option.equals("3")) {
				System.out.println("Updating name");
				UserService.changeUserName(scan);
				System.out.println("Your name has been changed, "+ username);
				break;
				}
			if(!option.equals("4")) {
				System.out.println("Wrong input.");
				}
		}
		}
	}
	
	
	
	

}
	


