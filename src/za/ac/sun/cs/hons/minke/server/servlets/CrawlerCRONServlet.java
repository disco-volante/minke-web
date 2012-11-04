package za.ac.sun.cs.hons.minke.server.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import za.ac.sun.cs.hons.minke.server.crawler.controller.StoreController;

@SuppressWarnings("serial")
public class CrawlerCRONServlet extends HttpServlet {
	private static final Logger _logger = Logger
			.getLogger(CrawlerCRONServlet.class.getName());
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		try {
			_logger.info("Cron Job has been executed");
			StoreController.run();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
