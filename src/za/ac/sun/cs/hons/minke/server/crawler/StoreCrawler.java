package za.ac.sun.cs.hons.minke.server.crawler;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Brand;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Category;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.DatePrice;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Product;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Branch;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Store;
import za.ac.sun.cs.hons.minke.server.dao.DAOService;
import za.ac.sun.cs.hons.minke.server.utils.EntityUtils;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class StoreCrawler extends WebCrawler {
	private HashMap<Category, HashSet<BranchProduct>> map;
	HashSet<Product> products;
	private Brand wooliesBrand;
	private Store woolies;
	private Branch online;
	private final String MATCH = "home/food-household/";
	private final Pattern FILTERS = Pattern
			.compile(".*(\\.(css|js|bmp|gif|jpe?g"
					+ "|png|tiff?|mid|mp2|mp3|mp4"
					+ "|wav|avi|mov|mpeg|ram|m4v|pdf"
					+ "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

	/**
	 * You should implement this function to specify whether the given url
	 * should be crawled or not (based on your crawling logic).
	 */
	@Override
	public boolean shouldVisit(WebURL url) {
		String href = url.getURL().toLowerCase();

		return !FILTERS.matcher(href).matches() && href.contains(MATCH);
	}

	@Override
	public void onStart() {
		map = new HashMap<Category, HashSet<BranchProduct>>();
		products = new HashSet<Product>();
		DAOService.init();
		wooliesBrand = EntityUtils.addBrand("Woolworths");
		woolies = EntityUtils.addStore("Woolworths");
		online = EntityUtils.addHolderBranch(woolies);
	}

	@Override
	public void onBeforeExit() {
		DAOService.init();
		EntityUtils.addBranchProducts(map);
	}

	/**
	 * This function is called when a page is fetched and ready to be processed
	 * by your program.
	 */
	@Override
	public void visit(Page page) {
		try {
			String url = page.getWebURL().getURL();
			if (url.endsWith("pid")
					&& page.getParseData() instanceof HtmlParseData) {
				String[] details = url.split("/");
				long pid = Long.parseLong(details[details.length - 1]
						.split("\\.")[0]);
				String sizePart = details[details.length - 2].substring(
						details[details.length - 2].lastIndexOf('-') + 1)
						.trim();
				if (sizePart.contains("x")) {
					return;
				}
				char[] chars = sizePart.toCharArray();
				double size = 0;
				String measure = "";
				int count = 0;
				for (char c : chars) {
					if (c != '.' && !Character.isDigit(c)) {
						break;
					}
					count++;
				}
				System.out.println(sizePart);
				System.out.println(count);
				size = Double.parseDouble(sizePart.substring(0, count));
				measure = sizePart.substring(count);
				String name = details[details.length - 2].substring(0,
						details[details.length - 2].lastIndexOf('-')).replace(
						'-', ' ');
				HtmlParseData htmlParseData = (HtmlParseData) page
						.getParseData();
				String html = htmlParseData.getHtml();
				Document doc = Jsoup.parse(html);
				Element priceElement = doc.getElementById("spanSkuPrice");
				if (priceElement == null || priceElement.text() == null
						&& priceElement.text().length() < 2) {
					return;
				}
				int price = (int) (Double.parseDouble(priceElement.text()
						.trim().substring(1)) * 100);
				String cat = details[details.length - 3].replace('-', ' ');
				Category c = new Category(cat);
				Product p = new Product(name, wooliesBrand, size, measure);
				p.setID(pid);
				BranchProduct bp = new BranchProduct(p, online, new DatePrice(
						new Date(), price, 0L));
				if (!map.containsKey(c)) {
					map.put(c, new HashSet<BranchProduct>());
				} else if (products.contains(p)) {
					return;
				}
				products.add(p);
				map.get(c).add(bp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
