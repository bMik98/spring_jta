package com.becomejavasenior.service;

import com.becomejavasenior.dao.OrderDao;
import com.becomejavasenior.dao.ProductDao;
import com.becomejavasenior.dao.UserDao;
import com.becomejavasenior.model.Order;
import com.becomejavasenior.model.Product;
import com.becomejavasenior.model.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/*
This class contains methods annotated with @Transactional
@author: sks
 */
public class TransactionalMethods {

	private OrderDao orderDaoImpl;
	private ProductDao productDaoImpl;
	private UserDao userDaoImpl;

	public TransactionalMethods(OrderDao orderDao, ProductDao productDao, UserDao userDao) {
		this.orderDaoImpl = orderDao;
		this.productDaoImpl = productDao;
		this.userDaoImpl = userDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Order findOrderById(Integer id) {
		return orderDaoImpl.getById(id);

	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Product findProductById(Integer id) {
		return productDaoImpl.getById(id);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User findUserById(Integer id) {
		return userDaoImpl.getById(id);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = NullPointerException.class)
	public long deleteItemsById(Integer id) {
		long time = System.nanoTime();
		orderDaoImpl.delete(findOrderById(id));
		productDaoImpl.delete(findProductById(id));
		userDaoImpl.delete(findUserById(id));
		return System.nanoTime() - time;
	}

	@Transactional
	public long updateProductById(Integer id) {
		long time = System.nanoTime();
		Product product = findProductById(id);
		product.setFvchar("UPDATED VALUE!");
		productDaoImpl.update(product);
		return System.nanoTime() - time;
	}
}
