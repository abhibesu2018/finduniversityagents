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


public class FindAgents {
	// public static DB db = new DB();

	private HashSet<String> links;
	private static ArrayList<String> universityLinkAndEmail = new ArrayList<String>();
	public FindAgents() {
		links = new HashSet<String>();
	}

	public void getPageLinks(String URL) throws Exception {
		// 4. Check if you have already crawled the URLs
		// (we are intentionally not checking for duplicate content in this example)
		if (!links.contains(URL)) {
			// System.err.println("Find the agents keyword in:"+URL);
			try {
				// 4. (i) If not add it to the index
				links.add(URL);
				System.out.println(URL);
				// 2. Fetch the HTML code
				Document document = Jsoup.connect(URL).get();
				// Check if the page contains keyword agents
				if (document.text().contains("agents") && document.text().contains("Global")) {
				//if (document.text().contains("Macquarie in your country")) {
					System.err.println("The page contains the keyword agents..:" + URL);
					System.out.println(URL);

					// 3. Parse the HTML to extract links to other URLs
					Elements linksOnPage = document.select("a[href]");

					// 5. For each extracted URL... go back to Step 4.
					for (Element page : linksOnPage) {
						if (page.attr("href").contains(URL.replace(".html", ""))) {
							System.out.println("The page to be searched :" + page.attr("abs:href"));
							//returnEmail(Jsoup.connect(page.attr("abs:href")).get());
							returnEmail(page.attr("abs:href"));
						}
					}
					if (universityLinkAndEmail!=null) {
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

	public static ArrayList<String> returnEmail(String url) throws IOException {
		Document doc = Jsoup.connect(url).get();
		Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(doc.toString());
		while (m.find()) {
			universityLinkAndEmail.add(url.concat(",").concat(m.group()));
		}
		return universityLinkAndEmail;
	}

	public static void main(String[] args) throws Exception {
		// 1. Pick a URL from the frontier
		new FindAgents().getPageLinks("https://sydney.edu.au/study/admissions/apply/global-agents.html");
	}
}
// }
