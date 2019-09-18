package com.web.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

import com.web.dao.OrderDetailDao;
import com.web.dao.OrderModelDao;
import com.web.dao.ProductDao;
import com.web.dao.StoreDao;
import com.web.model.Emp;
import com.web.model.OrderDetail;
import com.web.model.OrderModel;
import com.web.model.Product;
import com.web.model.Store;
import com.web.model.StoreDetail;
import com.web.query.StoreQuery;
import com.web.service.StoreService;
import com.web.utils.MD5Utils;

public class StoreServiceImpl extends BaseServiceImpl<Store, StoreQuery> implements StoreService {

	private StoreDao storeDao;
	
	//��ⶩ���������Ϣע��  Ϊ��Ʒ����ṩ֧��
	private ProductDao productDao;
	private OrderModelDao orderModelDao;
	private OrderDetailDao orderDetailDao;
	
	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
		this.baseDao = storeDao;
	}

	//��Ʒ������  �޸Ķ���״̬
	@Override
	public void updateInstock(Integer storeId, Integer productId,
			Integer productNum, Integer orderDetailId) {
		// TODO Auto-generated method stub
		//�鿴Ҫ���Ĳֿ��Ƿ���ڸ���Ʒ
		Store store = storeDao.getObj(storeId);
		//��ö�������ϸ
		OrderDetail orderDetail = orderDetailDao.getObj(orderDetailId);
		//��òֿ���ϸ
		Set<StoreDetail> details = store.getDetails();
		boolean isExist = false;
		for(StoreDetail sd : details){
			if(sd.getProduct().getProductId().intValue() == productId.intValue()){
				//�޸Ĳֿ����ϸ
				sd.setNum(sd.getNum()+productNum);
				//�޸Ķ��������ʣ������
				orderDetail.setSurplus(orderDetail.getSurplus() - productNum);
				isExist = true;
				break;
			}
		}
		//����ֿ���û��Ҫ������Ʒ��Ӧ���ڲֿ��в���һ����ϸ������һ����ϸ����
		if(!isExist){
			StoreDetail sd = new StoreDetail();
			sd.setNum(productNum);
			Product product = productDao.getObj(productId);
			sd.setProduct(product);
			sd.setStoreId(storeId);
			details.add(sd);
		}
				
		//��ö�����Ϣ
		String orderId = orderDetail.getOrderId();
		//��õ�ǰ��ϸ�����ж���
		OrderModel order = orderModelDao.getObj(new Integer(orderId));
		//�����������µ�������ϸ
		Set<OrderDetail> details2 = order.getDetails();
		boolean isFinish = true;
		for(OrderDetail od : details2){
			if(od.getSurplus().intValue() != 0){
				order.setOrderState(2);
				isFinish = false;
				break;
			}
		}
		if(isFinish){
			order.setOrderState(3);
		}
	}
	
	//�޸Ĳֿ���Ϣ
	@Override
	public void updateStore(Store store) {
		// TODO Auto-generated method stub
		Store store1 = storeDao.getObj(store.getStoreId());
		try {
			//��emp�е��������Կ�����emp1��  ����������ͬ���ᱨ��
			BeanUtils.copyProperties(store1, store);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		storeDao.update(store1);
	}

	/**
	 * set��get������-----------------------------------------------------------
	 * @return
	 */
	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public OrderModelDao getOrderModelDao() {
		return orderModelDao;
	}

	public void setOrderModelDao(OrderModelDao orderModelDao) {
		this.orderModelDao = orderModelDao;
	}

	public OrderDetailDao getOrderDetailDao() {
		return orderDetailDao;
	}

	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	public StoreDao getStoreDao() {
		return storeDao;
	}
}
