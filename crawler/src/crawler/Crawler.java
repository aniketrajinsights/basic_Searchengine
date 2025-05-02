package crawler;

import java.io.IOException;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	HashSet<String> urlSet;
	int max_depth = 2;

	Crawler() {
		urlSet = new HashSet<String>();
	}

	public void getPageTextsAndLinks(String url, int depth) {
		if (urlSet.contains(url)) {
			return;
		}
		if (depth > max_depth) {
			return;
		}
		depth++;

		try {
			Document document = Jsoup.connect(url).timeout(5000).get();


			System.out.println(document.title());

			Elements availbleLinksOnPage = document.select("a[href]");

			for (Element currentLink : availbleLinksOnPage) {
				getPageTextsAndLinks(currentLink.attr("abs:href"), depth);
			}

		} catch (IOException ioException) {

			ioException.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Crawler crawler = new Crawler();
		crawler.getPageTextsAndLinks("https://www.geeksforgeeks.org/", 1);
		crawler.getPageTextsAndLinks("https://www.codecademy.com/", 1);
		crawler.getPageTextsAndLinks("https://www.hackerrank.com/", 1);
		crawler.getPageTextsAndLinks("https://www.edx.org/", 1);
		crawler.getPageTextsAndLinks("https://www.pluralsight.com/browse", 1);
		crawler.getPageTextsAndLinks("https://www.tutorialspoint.com/", 1);

	}
}
