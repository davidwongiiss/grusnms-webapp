package com.device.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Read the url and return the content as a string.
 * 
 * @author luciali * Preferences - Java - Code Style - Code Templates
 */
public class HtmlPageUtil {
	private static final Logger log = new Logger();

	/**
	 * Read the url and return the content as a string.
	 * 
	 * @param url
	 *            Target url.
	 * @return The url Page content string.
	 */
	public static String getHTMLPage(String url) {
		StringBuffer sb = new StringBuffer();
		URL HttpUrl;
		URLConnection con;
		InputStreamReader inReader;
		BufferedReader reader;
		try {
			HttpUrl = new URL(url);
			con = HttpUrl.openConnection();
			inReader = new InputStreamReader(con.getInputStream());
			reader = new BufferedReader(inReader);

			String line = null;
			while ((line = reader.readLine()) != null) {
				if (line.equals(""))
					continue;
				sb.append(line);
				sb.append("\n");
			}
			reader.close();
			inReader.close();
			log.debug("get " + url + " ok!");
		} catch (Exception e) {
			log.error("fail to get " + url + " !");
			e.printStackTrace();

		}
		return sb.toString();
	}

	public static List<String> getHTMLpageStrList(String url) {
		URLConnection con;
		List<String> reList = null;
		try {
			URL HttpUrl = new URL(url);
			con = HttpUrl.openConnection();
			con.setUseCaches(false);
			reList = FileOperator.readInputStreamLine(new BufferedInputStream(
					con.getInputStream()));
		} catch (Exception e) {
			log.error("fail to get " + url + " !");
			e.printStackTrace();

		}
		return reList;
	}
}