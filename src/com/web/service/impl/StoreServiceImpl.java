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
	
	//入库订单的相关信息注入  为商品入库提供支持
	private ProductDao productDao;
	private OrderModelDao orderModelDao;
	private OrderDetailDao orderDetailDao;
	
	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
		this.baseDao = storeDao;
	}

	//商品入库操作  修改订单状态
	@Override
	public void updateInstock(Integer storeId, Integer productId,
			Integer productNum, Integer orderDetailId) {
		// TODO Auto-generated method stub
		//查看要入库的仓库是否存在该商品
		Store store = storeDao.getObj(storeId);
		//获得订单的明细
		OrderDetail orderDetail = orderDetailDao.getObj(orderDetailId);
		//获得仓库明细
		Set<StoreDetail> details = store.getDetails();
		boolean isExist = false;
		for(StoreDetail sd : details){
			if(sd.getProduct().getProductId().intValue() == productId.intValue()){
				//修改仓库的明细
				sd.setNum(sd.getNum()+productNum);
				//修改订单详情的剩余数量
				orderDetail.setSurplus(orderDetail.getSurplus() - productNum);
				isExist = true;
				break;
			}
		}
		//如果仓库中没有要入库的商品，应该在仓库中插入一条明细，创建一个明细对象
		if(!isExist){
			StoreDetail sd = new StoreDetail();
			sd.setNum(productNum);
			Product product = productDao.getObj(productId);
			sd.setProduct(product);
			sd.setStoreId(storeId);
			details.add(sd);
		}
				
		//获得订单信息
		String orderId = orderDetail.getOrderId();
		//获得当前明细的所有订单
		OrderModel order = orderModelDao.getObj(new Integer(orderId));
		//获得这个订单下的所有明细
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
	
	//修改仓库信息
	@Override
	public void updateStore(Store store) {
		// TODO Auto-generated method stub
		Store store1 = storeDao.getObj(store.getStoreId());
		try {
			//把emp中的所有属性拷贝到emp1中  两者主键相同，会报错
			BeanUtils.copyProperties(store1, store);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		storeDao.update(store1);
	}

	/**
	 * set和get方法区-----------------------------------------------------------
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
