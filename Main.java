import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		boolean exit = false;
		Scanner getip = new Scanner(System.in);
		System.out.println("Welcome to the User File application, developed by Mayank. You can access the multiple functions by choosing the options from list below.");
		System.out.println("");

		while (!exit) {
			System.out.println("Choose an option:");
			System.out.println("1. Get List of File Names");
			System.out.println("2. Add a user file");
			System.out.println("3. Delete a user file ");
			System.out.println("4. Search a file");
			System.out.println("5. Exit the application");
			int option = getip.nextInt();
			
			switch (option) {
				case 1:
					listFiles();
					break;
				case 2:
					createFile();
					break;
				case 3:
					deleteFile();
					break;
				case 4:
					searchFile();
					break;
				case 5:
					exit = true;
					System.out.println("Thank You. Exiting....");
					break;
				default:
					System.out.println("Incorrect option. Please chose again.");
			}
		}
		getip.close();
	}
	

	private static void listFiles() {
		File location = new File("/home/maanshuguptagma/java-apps/lesson1Project/user");
		String files[] = location.list();
		if(files.length>0) {
			Arrays.sort(files);
			System.out.println("List of the files:");
			for(String file: files) {
				System.out.println(file);
			}
		}else {
			System.out.println("No file found.");
		}
		System.out.println("");
	}
	
	private static String takefilename() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the file name : ");
		String str = scan.nextLine();
		return "user/"+str;
	}
	
	private static void createFile() {
		String str = takefilename();
		try {
			File file = new File(str);
			
			if(file.createNewFile()) {
				System.out.println("User File created");
				System.out.println("Now lets write some content in the file.");
				writeFile(str);
			}else {
				System.out.println("User File already exist");
			}
		}catch(IOException e) {
			System.out.println("Error while creating file. Try again.");
		}
		System.out.println("");
	}
	
	private static void writeFile(String str) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(str))){
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter the text you want to write to the file. You can press Enter to exit the file edittor and return to main menu. ");
			String content = scanner.nextLine();
			writer.write(content);
			System.out.println("Successfully wrote to  the file");
		} catch (IOException e) {
			System.out.println("Error occured while writing to the file");
//			e.printStackTrace();
		}
		System.out.println("");
	}
	
	private static void deleteFile() {
		String str = takefilename();
		try {
			Files.deleteIfExists(Paths.get(str));
			System.out.println("User File successfully deleted");
		}catch(IOException e) {
			System.out.println("Error: an error occured while deleting the file.");
//			e.printStackTrace();
		}
		System.out.println("");
	}
	
	private static void searchFile() {
		String str = takefilename();
		try(BufferedReader reader = new BufferedReader(new FileReader(str))){
			System.out.println("User file found. The content of the file is :");
			String line;
			while((line = reader.readLine())!=null) {
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
//			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error occured while finding the file");
//			e.printStackTrace();
		}
		System.out.println("");
	}

}
