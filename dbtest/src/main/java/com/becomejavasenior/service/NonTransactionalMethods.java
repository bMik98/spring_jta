package com.becomejavasenior.service;

import com.becomejavasenior.dao.OrderDao;
import com.becomejavasenior.dao.ProductDao;
import com.becomejavasenior.dao.UserDao;
import com.becomejavasenior.model.Order;
import com.becomejavasenior.model.Product;
import com.becomejavasenior.model.User;

/*
This class contains methods annotated with @Transactional
@author: sks
 */
public class NonTransactionalMethods {
	private OrderDao orderDaoImpl;
	private ProductDao productDaoImpl;
	private UserDao userDaoImpl;

	public NonTransactionalMethods(OrderDao orderDao, ProductDao productDao, UserDao userDao) {
		this.orderDaoImpl = orderDao;
		this.productDaoImpl = productDao;
		this.userDaoImpl = userDao;
	}

	public Order findOrderById(Integer id) {
		return orderDaoImpl.getById(id);

	}

	public Product findProductById(Integer id) {
		return productDaoImpl.getById(id);
	}

	public User findUserById(Integer id) {
		return userDaoImpl.getById(id);
	}

	public long deleteItemsById(Integer id) {
		long time = System.nanoTime();
		orderDaoImpl.delete(findOrderById(id));
		productDaoImpl.delete(findProductById(id));
		userDaoImpl.delete(findUserById(id));
		return System.nanoTime() - time;
	}

	public long updateProductById(Integer id) {
		long time = System.nanoTime();
		Product product = findProductById(id);
		product.setFvchar("UPDATED VALUE!");
		productDaoImpl.update(product);
		return System.nanoTime() - time;
	}
}
