package lockedme;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class LockedMeMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Scanner sc = new Scanner(System.in);
		String path = null;
		LockedMe lock = null;
		boolean ind = true;
		
		System.out.println("****Welcome to LockeMe.com****");
		System.out.println("Please enter the path for the main folder or enter -1 to use a default one.");
		//path = sc.next();
		path = 	"./default";		//"/home/eclipse-workspace/LockedMe/src/lockedme/LockedMe.java";
		
		while(ind){
			if(Files.exists(Path.of(path))) {
				lock = new LockedMe(path);
				ind = false;
			}else {
				System.out.println("invalid path, please try again.");
				System.out.println("Please enter the path for the main folder or enter -1 to use a default one.");
				path = "./Default";
			}
		}
			
		System.out.println(lock.toString());
	}

}
