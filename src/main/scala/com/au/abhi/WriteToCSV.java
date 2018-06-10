package com.au.abhi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteToCSV {

	public static StringBuilder csvRecords = new StringBuilder();
	public static final String FILE_NAME = "somefile.csv";

	public static void writeCSV(ArrayList<String> arr) {
		arr.add(0, "country, Web Page Link, EmailID");
		String eol = System.getProperty("line.separator");
		deleteFile(FILE_NAME);
		File file = new File(FILE_NAME);
		try (FileWriter writer = new FileWriter(file.getAbsoluteFile(),true)) {
			for (String str : arr) {
				writer.append(str).append(eol);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
	public static boolean deleteFile(String fileName) {
		boolean fileDeleted = true;
		if(checkIfFileExists(fileName)) {
			File file = new File(fileName);
			if(file.delete()) {
				System.out.println("File deleted successfully");
			}else {
				fileDeleted = false;
				System.out.println("File deletion failure");
			}
		}
		return fileDeleted;
	}
	
	public static boolean checkIfFileExists(String fileName) {
		File file = new File(fileName);
		boolean fileExists = true;
		if(!file.exists()) {
			fileExists = false;
			System.err.println("File not exists :"+fileName);
		}
		return fileExists;
	}
}
