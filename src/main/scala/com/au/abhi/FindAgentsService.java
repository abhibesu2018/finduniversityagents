package com.au.abhi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FindAgentsService {
	private HashSet<String> links = new HashSet<>();
	private static ArrayList<String> universityLinkAndEmail = new ArrayList<String>();

	public void getPageLinks(WebURLInformation webURLInformation) {
		// check if links are traversed already
		String URL = webURLInformation.getURL();
		if (!links.contains(URL)) {
			// System.err.println("Find the agents keyword in:"+URL);
			try {
				// URL added in the traversed links
				links.add(URL);
				// Fetch the HTML code from URL
				Document document = Jsoup.connect(URL).get();
				// Check if the page contains keyword agents
				if (descisionSearchString(document.text(), webURLInformation.getSearchString())) {
					Elements linksOnPage = document.select("a[href]");

					// For each linked page find out the required information
					for (Element page : linksOnPage) {
						if (page.attr("href").contains(URL.replace(".html", ""))) {
							System.out.println(page);
							getInformationFromURL(page.attr("abs:href"),webURLInformation.getCountry());
						}
					}
					// Write all the information to CSV file
					if (universityLinkAndEmail != null) {
						System.err.println("size: "+universityLinkAndEmail.size());
						WriteToCSV.writeCSV(universityLinkAndEmail);
					}
				}
			} catch (IOException e) {
				System.err.println("For IOException'" + URL + "': " + e.getMessage());
			} catch (IllegalArgumentException e) {
				System.err.println("For IllegalArgumentException'" + URL + "': " + e.getMessage());
			}

		}
	}

	public boolean descisionSearchString(String text, String searchString) {
		boolean isContainsSearchString = true;
		String[] strarray = searchString.split("_");
		for (String str : strarray) {
			if (!text.contains(str)) {
				isContainsSearchString = false;
				break;
			}
		}
		return isContainsSearchString;
	}

	public static ArrayList<String> getInformationFromURL(String url, String country) {
		Document doc;
		try {
			doc = Jsoup.connect(url).get();

			// Get the email addresses
			Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(doc.toString());
			while (m.find()) {
				StringBuilder sb = new StringBuilder();
				universityLinkAndEmail.add(sb.append(country).append(",").append(url).append(",").append(m.group()).toString());
			}
		} catch (IOException e) {
			System.err.println("IOException for URL :" + url);
		}
		return universityLinkAndEmail;
	}
}
