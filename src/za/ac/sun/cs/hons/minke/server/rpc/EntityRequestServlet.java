package za.ac.sun.cs.hons.minke.server.rpc;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityID;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.City;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Country;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Province;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Category;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.DatePrice;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Product;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Branch;
import za.ac.sun.cs.hons.minke.server.dao.DAOService;
import za.ac.sun.cs.hons.minke.server.util.EntityToXMLConverter;
import za.ac.sun.cs.hons.minke.server.util.EntityUtils;

public class EntityRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String auth = request.getParameter("authority");
		if (auth == null || !auth.equals("c5f4486abc034369842fc16a7b744085")) {
			return;
		}
		String requestType = request.getParameter("type");
		if (requestType == null) {
			return;
		}
		String ret = "";
		if (requestType.equals("_locations")) {
			List<City> cities = DAOService.cityDAO.listAll();
			List<Country> countries = DAOService.countryDAO.listAll();
			List<Province> provinces = DAOService.provinceDAO.listAll();
			ret = EntityToXMLConverter.convertLocations(cities, provinces,
					countries);
		} else if (requestType.equals("_products")) {
			List<Product> products = DAOService.productDAO.listAll();
			ret = EntityToXMLConverter.convertProducts(products);
		} else if (requestType.equals("_categories")) {
			List<Category> categories = DAOService.categoryDAO.listAll();
			ret = EntityToXMLConverter.convertCategories(categories);
		} else if (requestType.equals("locationcategory_branchproducts")) {
			HashSet<Branch> branches = getBranches(request);
			HashSet<Product> products = getProducts(request);
			ret = EntityToXMLConverter.convertBranchProducts(EntityUtils
					.getBranchProducts(products, branches));
		} else if (requestType.equals("locationproduct_branchproducts")) {
			HashSet<Branch> branches = getBranches(request);
			HashSet<Long> products = getIDs(request, "product");
			ret = EntityToXMLConverter.convertBranchProducts(EntityUtils
					.getBranchProductsByID(products, branches));
		} else if (requestType.equals("product_branches")) {
			HashSet<Long> products = getIDs(request, "product");
			HashMap<Branch, HashMap<BranchProduct, List<DatePrice>>> result = EntityUtils
					.getProductBranches(products);
			ret = EntityToXMLConverter.convertBranchShopping(result);

		} else if (requestType.equals("coords_branches")) {
			double latitude = getParams(request, "latitude");
			double longitude = getParams(request, "longitude");
			ret = EntityToXMLConverter.convertBranches(EntityUtils
					.getCoordsBranches(latitude, longitude));
		} else if (requestType.equals("branch_branchproduct")) {
			Long branchCode = (long) getParams(request, "branch");
			Long barCode = (long) getParams(request, "barcode");
			BranchProduct bp = EntityUtils
					.getBranchProduct(branchCode, barCode);
			if (bp != null) {
				ret = EntityToXMLConverter.convert(bp, null);
			}
		} else if (requestType.equals("branchproduct_branchproduct")) {
			ret =  EntityToXMLConverter
					.convertBranchProducts(addBranchProduct(request));
		} else if (requestType.equals("_brands")) {

			ret = EntityToXMLConverter.convertBrands(EntityUtils.getBrands());
		} else if (requestType.equals("price_branchproduct")) {
			ret = EntityToXMLConverter
					.convertBranchProducts(updateBranchProduct(request));

		}
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(ret);
	}

	private Map<BranchProduct, List<DatePrice>> updateBranchProduct(HttpServletRequest request) {
		String sid = request.getParameter("id");
		String sprice = request.getParameter("price");
		if (sid != null && sprice != null) {
			long id = Long.parseLong(sid);
			double price = Double.parseDouble(sprice);
			EntityUtils.updateBranchProduct(id, (int) (price * 100));
			return EntityUtils.getBranchProduct(id);
		}
		return null;
	}

	private Map<BranchProduct, List<DatePrice>> addBranchProduct(HttpServletRequest request) {
		String param = request.getParameter("entity_type");
		if (param != null && param.equals("branchproduct")) {
			String product = request.getParameter("product");
			String brand = request.getParameter("brand");
			String sprice = request.getParameter("price");
			String ssize = request.getParameter("size");
			String sbarCode = request.getParameter("barcode");
			String sbranchCode = request.getParameter("branchcode");
			String measure = request.getParameter("measure");
			if (product != null && brand != null && sprice != null
					&& ssize != null && sbarCode != null && sbranchCode != null
					&& measure != null) {
				double price = Double.parseDouble(sprice);
				double size = Double.parseDouble(ssize);
				long barCode = Long.parseLong(sbarCode);
				long branchCode = Long.parseLong(sbranchCode);
				return EntityUtils
						.addBranchProduct(product, brand, (int) (price * 100),
								size, measure, barCode, branchCode);
			}
		}
		return null;
	}

	private HashSet<Product> getProducts(HttpServletRequest request) {
		HashSet<Long> categoryIds = getIDs(request, "category");
		return EntityUtils.getProducts(categoryIds);
	}

	private HashSet<Branch> getBranches(HttpServletRequest request) {
		HashMap<EntityID, HashSet<Long>> locIds = new HashMap<EntityID, HashSet<Long>>();
		locIds.put(EntityID.CITY, getIDs(request, "city"));
		locIds.put(EntityID.PROVINCE, getIDs(request, "province"));
		locIds.put(EntityID.COUNTRY, getIDs(request, "country"));
		return EntityUtils.getLocationBranches(locIds);
	}

	private HashSet<Long> getIDs(HttpServletRequest request, String entityType) {
		String param = request.getParameter(entityType);
		HashSet<Long> ids = new HashSet<Long>();
		if (param != null) {
			String[] sIds = request.getParameter(entityType).split(",");
			for (String s : sIds) {
				if (s != null && s.matches("([1-9][0-9]*)")) {
					ids.add(Long.parseLong(s));
				}
			}
		}
		return ids;
	}

	private double getParams(HttpServletRequest request, String entityType) {
		String param = request.getParameter(entityType);
		if (param != null) {
			for (String s : request.getParameter(entityType).split(",")) {
				if (s.matches("([1-9][0-9]*)+(\\.[0-9]+)?")) {
					return Double.parseDouble(s);
				}
			}
		}
		return 0;
	}

}