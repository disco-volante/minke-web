package za.ac.sun.cs.hons.minke.server.dao;

/**
 * 
 * @author godfried
 * 
 */
public class DAOService {
	public static CityDAO cityDAO;
	public static BrandDAO brandDAO;
	public static ProductCategoryDAO productCategoryDAO;
	public static CityLocationDAO cityLocationDAO;
	public static BranchDAO branchDAO;
	public static BranchProductDAO branchProductDAO;
	public static DatePriceDAO datePriceDAO;
	public static ProductDAO productDAO;
	public static StoreDAO storeDAO;
	public static CategoryDAO categoryDAO;
	public static CountryDAO countryDAO;
	public static ProvinceDAO provinceDAO;
	public static LocationDAO locationDAO;
	public static EntityNameMapDAO entityMapDAO;
	private static boolean initialised = false;

	/**
     * 
     */
	public static void init() {
		if (!initialised) {
			locationDAO = new LocationDAO();
			countryDAO = new CountryDAO();
			provinceDAO = new ProvinceDAO();
			cityDAO = new CityDAO();
			cityLocationDAO = new CityLocationDAO();
			brandDAO = new BrandDAO();
			categoryDAO = new CategoryDAO();
			productDAO = new ProductDAO();
			productCategoryDAO = new ProductCategoryDAO();
			storeDAO = new StoreDAO();
			branchDAO = new BranchDAO();
			datePriceDAO = new DatePriceDAO();
			branchProductDAO = new BranchProductDAO();
			entityMapDAO = new EntityNameMapDAO();
			initialised = true;
		}

	}

}
