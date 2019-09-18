package com.web.service;

import com.web.model.Store;
import com.web.query.StoreQuery;

public interface StoreService extends BaseService<Store, StoreQuery>{
	//��Ʒ����������
	public void updateInstock(Integer storeId, Integer productId, Integer productNum, Integer orderDetailId);
	
	public void updateStore(Store store);
}
