package za.ac.sun.cs.hons.minke.client.util;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.DatePrice;

public class UtilsTest {

	@Test
	public void calcTotalTest() {
		Map<Long, Integer> qty = getMockQuantityMap();
		Set<BranchProduct> bps = getMockBranchProducts(qty);
		double total = Utils.calcTotal(bps, qty);
		double actual = 0;
		for(BranchProduct bp : bps){
			actual  += qty.get(bp.getProductID())*bp.getDatePrice().getActualPrice();
		}
		assertEquals(actual, total, 0.001);
		total = Utils.calcTotal(null, null);
		assertEquals(0, total, 0.001);
		total = Utils.calcTotal(bps, null);
		assertEquals(0, total, 0.001);
		total = Utils.calcTotal(null, qty);
		assertEquals(0, total, 0.001);
		total = Utils.calcTotal(new HashSet<BranchProduct>(), qty);
		assertEquals(0, total, 0.001);
		total = Utils.calcTotal(new HashSet<BranchProduct>(), new HashMap<Long, Integer>());
		assertEquals(0, total, 0.001);
		total = Utils.calcTotal(bps, new HashMap<Long, Integer>());
		assertEquals(0, total, 0.001);	
	}
	
	@Test
	public void regexTest(){
		assertTrue(REGEX.DECIMALS_0.test("0asds"));
		assertTrue(REGEX.DECIMALS_0.test("0"));
		assertTrue(REGEX.DECIMALS_0.test("0.0"));
		assertTrue(REGEX.DECIMALS_0.test("90"));
		assertTrue(REGEX.DECIMALS_0.test("90.00"));
		assertFalse(REGEX.DECIMALS_0.test("0.099"));
		assertFalse(REGEX.DECIMALS_0.test("0.010"));
		assertFalse(REGEX.DECIMALS_0.test("-0.0"));
		assertFalse(REGEX.DECIMALS_0.test("-10"));
		assertFalse(REGEX.DECIMALS_0.test("-10.0"));
		assertTrue(REGEX.DECIMALS_0.test("0.10"));
		assertFalse(REGEX.DECIMALS_0.test(".10"));
		assertTrue(REGEX.DECIMALS_0.test("032.0"));
		assertTrue(REGEX.DECIMALS_1.test("90.0099"));
		assertFalse(REGEX.DECIMALS_1.test("10.099999999000"));
		assertTrue(REGEX.DECIMALS_1.test("0.010"));
		assertTrue(REGEX.STRING.test("g--a''g  AA a'a,a-Baa s"));
		assertFalse(REGEX.STRING.test("12a"));
		assertFalse(REGEX.STRING.test("12  "));
		assertFalse(REGEX.STRING.test("?  "));
	}

	private Set<BranchProduct> getMockBranchProducts(Map<Long, Integer> qty) {
		Set<BranchProduct> set =  new HashSet<BranchProduct>();
		for(long id : qty.keySet()){
			DatePrice dp = new DatePrice(new Date(),(int)id,-1);
			BranchProduct bp = new BranchProduct(null,null,dp);
			bp.setProductID(id);
			set.add(bp);
		}
		return set;
	}

	private Map<Long, Integer> getMockQuantityMap() {
		Map<Long, Integer> map =  new HashMap<Long, Integer>();
		for(int i = 0; i < 10; i ++){
			map.put((long) i, i);
		}
		return map;
	}

}
