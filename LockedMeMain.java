package lockedme;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LockedMeMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String path = null;
		LockedMe lock = null;
		boolean ind = true;
		int option = 0;
		
		System.out.println("****Welcome to LockeMe.com****");
		System.out.println("\nPlease enter the path for the main folder or enter -1 to use a default one.");
		//path = sc.next();
		path = 	"./Default";		//"/home/eclipse-workspace/LockedMe/src/lockedme/LockedMe.java";
		
		while(ind){
			if(Files.exists(Path.of(path))) {
				lock = new LockedMe(path);
				ind = false;
				System.out.println("\nPath: \"" + lock.getPath1().toString() + "\" has been selected\n");
			}else {
				System.out.println("invalid path, please try again.");
				System.out.println("Please enter the path for the main folder or enter -1 to use a default one.");
			}
		}
		
		while(option != 4) {
			System.out.println(printMainMenu());
			try{
				option = sc.nextInt();
			}catch(InputMismatchException e) {
				option = -1;
				sc = new Scanner(System.in);
			}
			
			if(validateOption(option)) {
				
			}else {
				System.out.println("\nPlease enter a correct option, try again!\n");
			}
		}
		
		System.out.println("\n*****Thank you for using LockedMe.com*****");
		
	}
	
	private static boolean validateOption(int option) {
		// TODO Auto-generated method stub
		if(option > 0 && option < 5) {
			return true;
		}else{
			return false;
		}
	}

	private final static void clearConsole()
	{
	    try
	    {
	        final String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	            Runtime.getRuntime().exec("cls");
	        }
	        else
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (IOException e)
	    {
	    	e.printStackTrace();
	    }
	}
	
	private static String printMainMenu() {
		StringBuilder sb = new StringBuilder();
		
		return sb.append("Select the option in the menu by pressing the respective number: \n\n").append("\t1. List Files\n")
				.append("\t2. File Operations.\n").append("\t3. Search File.\n").append("\t4. Exit.").toString();
	}

}
