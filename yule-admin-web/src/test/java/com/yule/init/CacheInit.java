package com.yule.init;

import org.junit.Test;

import com.yule.cached.AreaInit;
import com.yule.cached.CompanyInit;
import com.yule.cached.UserInit;

public class CacheInit {

	@Test
	public void init(){
		try {
			AreaInit.initArea();
			CompanyInit.init();
			UserInit.initUserLevel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
