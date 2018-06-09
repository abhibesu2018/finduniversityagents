package com.au.abhi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class WriteToCSV {

	public static StringBuilder csvRecords = new StringBuilder();

	public static void writeCSV(ArrayList<String> arr) throws Exception {
		arr.add(0, "Web Page Link, EmailID");
		String eol = System.getProperty("line.separator");
		try (Writer writer = new FileWriter("somefile.csv")) {
			for (String str : arr) {
				writer.append(str).append(eol);
			}
		} catch (IOException ex) {
			ex.printStackTrace(System.err);
		}
	}

	/*
	 * public static void writeCSV(HashMap<String, String> map) throws Exception {
	 * String eol = System.getProperty("line.separator");
	 * 
	 * try (Writer writer = new FileWriter("somefile.csv")) { for (Map.Entry<String,
	 * String> entry : map.entrySet()) { writer.append(entry.getKey()) .append(',')
	 * .append(entry.getValue()) .append(eol); } } catch (IOException ex) {
	 * ex.printStackTrace(System.err); } }
	 */

	/*
	 * public static void main(String[] args) {
	 * 
	 * String header = WriteCsvHeader(new String[]{"Web Page Link", "EmailID"});
	 * WriteCsvRecords(new String[]{"www.google1.com", "test1@gmail.com"});
	 * WriteCsvRecords(new String[]{"www.google2.com", "test2@gmail.com"});
	 * 
	 * 
	 * 
	 * try { WriteToFile(header + csvRecords.toString(), "CsvStringBuilder1.csv"); }
	 * catch (IOException e) { e.printStackTrace(); } }
	 */

	public static void WriteToFile(String fileContent, String fileName) throws IOException {
		String projectPath = System.getProperty("user.dir");
		String tempFile = projectPath + File.separator + fileName;
		File file = new File(tempFile);
		// if file does exists, then delete and create a new file
		if (file.exists()) {
			try {
				File newFileName = new File(projectPath + File.separator + "backup_" + fileName);
				file.renameTo(newFileName);
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(fileContent);
		System.out.println(fileContent);
		bw.close();

	}

	public static String WriteCsvHeader(String[] headers) {
		StringBuilder csvHeader = new StringBuilder();
		int totalHeaders = headers.length;
		for (int i = 0; i < totalHeaders; i++) {
			// if not last header, append comma after each header
			if (i != totalHeaders - 1) {
				csvHeader.append(headers[i]);
				csvHeader.append(",");
			}
			// if last header, just append the column
			else {
				csvHeader.append(headers[i]);
			}
		}
		csvHeader.append("\n");
		return csvHeader.toString();
	}

	public static void WriteCsvRecords(String[] records) {
		int numberOfColumnsInRecords = records.length;
		for (int i = 0; i < numberOfColumnsInRecords; i++) {
			// if not the last column, append comma after each column
			if (i != numberOfColumnsInRecords - 1) {
				csvRecords.append(records[i]);
				csvRecords.append(",");
			}
			// if last column, just append the column
			else {
				csvRecords.append(records[i]);
			}
		}
		csvRecords.append("\n");
	}
}
