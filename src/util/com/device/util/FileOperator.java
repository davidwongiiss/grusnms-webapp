package com.device.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 文件的读写
 * 
 * @author gangma
 * 
 */
public class FileOperator {

	/**
	 * 把文件中的内容一行行读出放在List中 fullFileName //带路径的文件名
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static List<String> readFileLine(String fullFileName)
			throws FileNotFoundException, IOException {
		BufferedReader infile = null;
		List<String> recordList = new ArrayList<String>();
		try {
			infile = new BufferedReader(new FileReader(fullFileName));
			String item;
			while ((item = infile.readLine()) != null) {
				if (!item.trim().equalsIgnoreCase("")) {
					recordList.add(item);
				}

			}
		} finally {
			if (infile != null) {
				infile.close();
			}
		}
		return recordList;
	}

	public static List<String> readInputStreamLine(InputStream stream)
			throws FileNotFoundException, IOException {
		BufferedReader infile = null;
		List<String> recordList = new ArrayList<String>();
		try {
			infile = new BufferedReader(new InputStreamReader(stream));
			String item;
			while ((item = infile.readLine()) != null) {
				if (!item.trim().equalsIgnoreCase("")) {
					recordList.add(item);
				}

			}
		} finally {
			if (infile != null) {
				infile.close();
			}
			if (stream != null) {
				stream.close();
			}
		}
		return recordList;
	}

	/**
	 * List的内容 一行行写入文件中 fullFileName //带路径的文件名
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void writeFileLine(String fullFileName, List recordList)
			throws FileNotFoundException, IOException {
		BufferedWriter outfile = null;

		try {
			outfile = new BufferedWriter(new FileWriter(fullFileName));
			for (Iterator iter = recordList.iterator(); iter.hasNext();) {
				String element = (String) iter.next();
				outfile.write(element);
			}

		} finally {
			if (outfile != null) {
				outfile.close();
			}
		}
	}

	/**
	 * yyyy-mm-dd hh:mm:ss 和yyyymmddhhmmss之间的转换
	 * 
	 * @param startDayStr
	 * @return
	 */
	public static String switchDateFormat(String startDayStr) {
		String result = "";
		if (startDayStr.length() == 14) {
			result += startDayStr.substring(0, 4);
			result += "-";
			result += startDayStr.substring(4, 6);
			result += "-";
			result += startDayStr.substring(6, 8);
			result += " ";
			result += startDayStr.substring(8, 10);
			result += ":";
			result += startDayStr.substring(10, 12);
			result += ":";
			result += startDayStr.substring(12, 14);
		} else if (startDayStr.length() > 14) {

			String[] item = startDayStr.trim().split(" ");
			String[] item1 = item[0].split("-");
			String[] item2 = item[1].split(":");
			result += item1[0];
			result += item1[1].length() == 2 ? item1[1] : "0" + item1[1];
			result += item1[2].length() == 2 ? item1[2] : "0" + item1[2];
			result += item2[0].length() == 2 ? item2[0] : "0" + item2[0];
			result += item2[1].length() == 2 ? item2[1] : "0" + item2[1];
			result += item2[2].length() == 2 ? item2[2] : "0" + item2[2];
		}
		return result;
	}

	// 下面是一个批量修改文件名的程序

	/***************************************************************************
	 * 
	 */
	public static void clearFileName(String path, String fromName,
			String toName, String[] deleteName) {
		deleteFile(path, deleteName);
		reNameFile(path, fromName, toName);
	}

	public static void reNameFile(String path, String fromName, String toName) {
		File baseDir = new File(path);
		File[] fileList = baseDir.listFiles();
		try {
			if (fileList != null) {
				// Loop through all the files and directory to delete them
				for (int count = 0; count < fileList.length; count++) {
					if (fileList[count].isFile()) {
						int pointPosition = fileList[count].getName()
								.lastIndexOf(".");

						if (pointPosition == -1) {
							continue;
						}

						String fileNameExt = fileList[count].getName()
								.substring(pointPosition + 1);

						if (fromName.equalsIgnoreCase(fileNameExt)) {

							System.out.println(fileList[count].getParent()
									+ "/"
									+ fileList[count].getName().replaceAll(
											fromName, toName));

							File tofile = new File(fileList[count].getParent()
									+ "/"
									+ fileList[count].getName().replaceAll(
											fromName, toName));

							System.out.println("rename file="
									+ fileList[count].getName() + " "
									+ fileList[count].renameTo(tofile));
							tofile = null;
							fileList[count] = null;
							continue;
						}

					} else {

						reNameFile(fileList[count].toString(), fromName, toName);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteFile(String path, String[] deleteName) {
		File baseDir = new File(path);
		File[] fileList = baseDir.listFiles();
		try {
			if (fileList != null) {
				// Loop through all the files and directory to delete them
				for (int count = 0; count < fileList.length; count++) {
					if (fileList[count].isFile()) {
						int pointPosition = fileList[count].getName()
								.lastIndexOf(".");
						if (pointPosition == -1) {
							continue;
						}
						String fileNameExt = fileList[count].getName()
								.substring(pointPosition + 1);
						for (int i = 0; i < deleteName.length; i++) {
							if (deleteName[i].equalsIgnoreCase(fileNameExt)) {
								System.out.println("delete file="
										+ fileList[count].getName() + " "
										+ (fileList[count].delete()));
								continue;
							}
						}

					} else {

						deleteFile(fileList[count].toString(), deleteName);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteall(String path) {
		File baseDir = new File(path);
		File[] fileList = baseDir.listFiles();
		try {
			if (fileList != null) {
				// Loop through all the files and directory to delete them
				for (int count = 0; count < fileList.length; count++) {
					if (fileList[count].isFile()) {
						System.out.println("delete file="
								+ fileList[count].getName() + " "
								+ (fileList[count].delete()));

					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteDirectory(String path, String[] deleteName) {
		File baseDir = new File(path);
		File[] fileList = baseDir.listFiles();
		try {
			if (fileList != null) {
				// Loop through all the files and directory to delete them
				for (int count = 0; count < fileList.length; count++) {
					if (fileList[count].isDirectory()) {
						String directoryName = fileList[count].getName();

						for (int i = 0; i < deleteName.length; i++) {
							if (deleteName[i].equalsIgnoreCase(directoryName)) {
								deleteall(fileList[count].toString());
								System.out.println("delete Directory="
										+ fileList[count].toString() + " "
										+ fileList[count].delete());
								continue;
							} else {
								deleteDirectory(fileList[count].toString(),
										deleteName);
							}
						}

					} else {
						continue;

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		deleteDirectory("D:\\peak_auto\\auto",
                new String[] { "CVS" });
	}

}
