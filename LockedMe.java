package lockedme;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
	
	public Path getPath1() {
		return path1;
	}

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
		String[] fileArray = null;
		
		try(Stream<Path> s = Files.list(path1)){
			fileArray = s.map(x -> x.getFileName().toString()).toArray(String[]::new);
			
			bubbleSort(fileArray);
			
		} catch (IOException e) {
			System.err.println("Error at reading files");
		}
		
		if (binarySearch(fileArray, fileName) >= 0) {
			System.out.println("\nFile " + fileName + " has been found");
		}else {
			System.out.println("\nFile " + fileName + " has not been found");
		}
	}
	
	private int binarySearch(String arr[], String value) {
		int start = 0;
		int end = arr.length - 1;
		int middle = (start+end)/2;
		
		while(!(arr[middle].equals(value)) && start <= middle) {
			if(value.compareToIgnoreCase(arr[middle]) < 0) {	
				end = middle - 1;
			}else {
				start = middle + 1;
			}
			middle = (start+end)/2;
		}
		
		if(!arr[middle].equals(value)) {
			return -1;
		}
		
		return middle;
	}
}
