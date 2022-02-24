package lockedme;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class LockedMe {
	private Path path1;
	private Path defPath;
	
	public LockedMe(String path) {
		defPath = Path.of("./Default");
		
		if(path.equals("-1")) {
			path1 = defPath;
		}else {
			path1 = Path.of(path);
			System.out.println(Files.exists(path1));
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
	
	

}
