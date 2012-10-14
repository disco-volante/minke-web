package za.ac.sun.cs.hons.minke.server.crawler.controller;

import za.ac.sun.cs.hons.minke.server.crawler.StoreCrawler;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class StoreController {
	public static void run() throws Exception {

		CrawlConfig config1 = new CrawlConfig();

		config1.setPolitenessDelay(200);
		config1.setCrawlStorageFolder("/home/crawler");
		config1.setMaxPagesToFetch(1000);

		PageFetcher pageFetcher1 = new PageFetcher(config1);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig,
				pageFetcher1);

		CrawlController controller1 = new CrawlController(config1,
				pageFetcher1, robotstxtServer);
		String[] crawler1Domains = new String[] { "http://www.woolworths.co.za/" };
		controller1.setCustomData(crawler1Domains);
		controller1.addSeed("http://www.woolworths.co.za/");
		controller1.startNonBlocking(StoreCrawler.class, 1);
		controller1.waitUntilFinish();

	}
	public static void main(String [] args) throws Exception {
	run();
	}
}
