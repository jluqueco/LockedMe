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

		//path = sc.next();
		path = 	"./Default";
		
		while(ind){
			if(Files.exists(Path.of(path))) {
				lock = new LockedMe(path);
				ind = false;
				System.out.println("\nPath: \"" + lock.getPath1().toString() + "\" has been selected\n");
			}else {
				System.out.println("invalid path, please try again.");
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
			
			if(validateOption(option,1,4)) {
				switch(option) {
					case 1 :
						System.out.println("\nFolder Contents:");
						lock.listContents();
						break;
					case 2 :
						while(option != 3) {
							System.out.println(operations());
							try{
								option = sc.nextInt();
							}catch(InputMismatchException e) {
								option = -1;
								sc = new Scanner(System.in);
							}
							while(!validateOption(option, 1, 3)) {
								System.out.println("\nPlease select a correct option");
							}
							if(option != 3) {
								lock.operation(option, fileName());
							}
						}
						break;
					case 3:
						lock.search(fileName());
				}
			}else {
				System.out.println("\nPlease enter a correct option, try again!\n");
			}
		}
		
		System.out.println("\n*****Thank you for using LockedMe.com*****");
		
	}
	
	private static String fileName() {
		Scanner fn = new Scanner(System.in);
		System.out.println("Please enter the filename: ");
		
		return fn.nextLine();
	}

	private static String operations() {
		StringBuilder sb = new StringBuilder();
		
		return sb.append("\nSelect the option: \n\n").append("\t1. Add File\n")
				.append("\t2. Delete a file.\n").append("\t3. Return to Main Menu").toString();
		
	}

	private static boolean validateOption(int option, int min, int max) {
		// TODO Auto-generated method stub
		if(option >= min && option <= max) {
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
		
		return sb.append("\nSelect the option in the menu by pressing the respective number: \n\n").append("\t1. List Files\n")
				.append("\t2. File Operations.\n").append("\t3. Search File.\n").append("\t4. Exit.").toString();
	}

}
