package lockedme;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class LockedMe {
	
	private static final Path defPath = Path.of("./Default");
	private Path path1;
	
	public LockedMe(String path) {
		
		if(path.equals("-1")) {
			path1 = defPath;
		}else {
			path1 = Path.of(path);
			//System.out.println(Files.exists(path1));
		}
	}
	
	/**
	 * @return the path1
	 */
	public Path getPath1() {
		return path1;
	}

	/**
	 * @return the defPath
	 */
	public Path getDefPath() {
		return defPath;
	}

	@Override
	public String toString() {
		for(int i = 0; i < path1.getNameCount(); i++) {
			System.out.println("Element " + i + " is: " + path1.getName(i));
		}
		
		return "LockedMe [path1=" + path1.getFileName() + ", defPath=" + defPath + "]";
	}

	public void listContents() {
		
		try(Stream<Path> s = Files.list(path1)){
			Object[] fileArray = s.map(x -> x.getFileName().toString()).toArray(String[]::new);
			
			bubbleSort((String[]) fileArray);
			for(Object i : fileArray) {
				System.out.println(i);
			}
		} catch (IOException e) {
			System.err.println("Error at reading files");
		}
		
	}
	
	private void bubbleSort(String[] array) {
		int n = array.length;
		for (int i = 0; i < n-1; i++) {
			for (int j = 0 ; j < n-i-1;j++) {
				if ( array[j].toLowerCase().compareTo(array[j+1].toLowerCase()) > 0) {
					String temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
	}

	public void operation(int option, String string) {
		Path filePath = Paths.get(path1.toString(), string);
		if(option == 1) {
			try {
				Files.createFile(filePath);
				System.out.println("File " + filePath.toString() + " has been created");
			} catch (IOException e) {
				System.err.println("*****File " + filePath.toString() + " already exists, please try again*****");
			}
		}else if(option == 2) {
			try {
				if(Files.deleteIfExists(filePath)) {
					System.out.println("\nFile \"" + filePath.toString() + "\" has been deleted");
				}else {
					System.err.println("\nFile \"" + filePath.toString() + "\" does not exists");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void search(String fileName) {
		Path filePath = Paths.get(path1.toString(), fileName);
		
		if(Files.exists(filePath)) {
			System.out.println("\nFile " + filePath +  "\" was found.");
		}else {
			System.out.println("\nFile " + filePath +  "\" was not found.");
		}
		
	}
}
