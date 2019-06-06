package com.engineer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestClass {
	
	SimpleCalculator obj = new SimpleCalculator();
	
	@Test
	public void onePlusOne () {
		assertEquals(2, obj.add(1,1));
	}
}
