package recursion;

import java.io.File;
import java.util.Scanner;

public class RecursionWithFiles {
	public static int counter = 0;

	public static void main(String[] args) {
		System.out.println("1) Calculate Disk Usage");
		System.out.println("2) List Files in Directory");
		System.out.print("\nChoose an option: ");

		Scanner in = new Scanner(System.in);
		String input = in.next();

		switch (input) {
		case "1":
			File file = new File(System.getProperty("user.dir"));
			System.out.println(diskUsage(file) + " bytes");
			break;
		case "2":
			Scanner in2 = new Scanner(System.in);

			System.out.print("Desktop, Documents, or Downloads? ");
			String parent = in2.nextLine();

			System.out.print("Child directory in " + parent + ": ");
			String child = in2.nextLine();

			System.out.print("File Extension: ");
			String extension = in2.nextLine();

			System.out.println("/Users/codyworthe/" + parent + "/" + child);
			File startingDirectory = new File("/Users/codyworthen/" + parent + "/" + child);

			find(startingDirectory, extension);
			System.out.println(counter + " " + extension + " files found in " + parent + "/" + child);

			in2.close();
			break;
		default:
			break;
		}

		in.close();
	}

	public static long diskUsage(File root) {
		long total = root.length();

		if (root.isDirectory()) {
			for (String childName : root.list()) {
				File child = new File(root, childName);
				total += diskUsage(child);
			}
		}
		System.out.println(total + "\t" + root);
		return total;
	}

	public static void find(File file, String extension) {
		if (file.isDirectory()) {
			for (File child : file.listFiles()) {
				find(child, extension);
			}
		} else {
			String fileName = file.toString();
			if (fileName.endsWith(extension)) {
				System.out.println(fileName);
				counter++;
			}
		}
	}

}
