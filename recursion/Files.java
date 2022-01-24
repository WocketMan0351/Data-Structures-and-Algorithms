package recursion;

import java.io.File;
import java.util.Scanner;

public class Files {

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

}
