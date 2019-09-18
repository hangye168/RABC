package com.web.service.impl;

import com.web.dao.StoreDetailDao;
import com.web.model.StoreDetail;
import com.web.query.StoreDetailQuery;
import com.web.service.StoreDetailService;

public class StoreDetailServiceImpl extends BaseServiceImpl<StoreDetail, StoreDetailQuery> implements StoreDetailService {

	private StoreDetailDao storeDetailDao;
	
	public void setStoreDetailDao(StoreDetailDao storeDetailDao) {
		this.storeDetailDao = storeDetailDao;
		this.baseDao = storeDetailDao;
	}
	
	

}
