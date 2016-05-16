package com.becomejavasenior.controller;

import com.becomejavasenior.dao.OrderDao;
import com.becomejavasenior.dao.ProductDao;
import com.becomejavasenior.dao.UserDao;
import com.becomejavasenior.model.DataCreator;
import com.becomejavasenior.service.NonTransactionalMethods;
import com.becomejavasenior.service.TransactionalMethods;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Random;
import java.util.Scanner;

public class AppGenerator {

	public static final int PACKAGE_SIZE = 500;
	private ApplicationContext context;
	private DataCreator dataCreator;
	private static TransactionalMethods transactionalMethods;
	private static NonTransactionalMethods nonTransactionalMethods;

	public AppGenerator(String xmlConfigFileName) {
		context = new ClassPathXmlApplicationContext(xmlConfigFileName);
		dataCreator = (DataCreator) context.getBean("dataCreator");
		transactionalMethods = (TransactionalMethods) context.getBean("transactionalMethods");
		nonTransactionalMethods = (NonTransactionalMethods) context.getBean("nonTransactionalMethods");
	}

	public static void main(String[] args) {
		AppGenerator appGenerator = new AppGenerator("beans.xml");
		while (true) {
			System.out.println("=========================\nOk! Choose action:\n" +
					"   1. Fill database tables.\n" +
					"   2. Test database operations(version1).\n" +
					"   3. Test database operations(version2).\n" +
					"   5. Exit.");
			Scanner scanner = new Scanner(System.in);
			String value = scanner.nextLine();
			switch (value) {
				case "1": {
					appGenerator.fillTables();
					break;
				}
				case "2": {
					Random random = new Random();
					long ntu = 0, ntd = 0, tu = 0, td = 0;
					for (long i = 0; i < 10; i++) {
						ntd += nonTransactionalMethods.deleteItemsById(random.nextInt(10000));
						ntu += nonTransactionalMethods.updateProductById(random.nextInt(10000));
						td += transactionalMethods.deleteItemsById(random.nextInt(10000));
						tu += transactionalMethods.updateProductById(random.nextInt(10000));
					}
					System.out.printf("Non-transactional method updated for %d nanoseconds\n",ntu/10);
					System.out.printf("Non-transactional method deleted for %d nanoseconds\n",ntd/10);
					System.out.printf("Transactional method updated for     %d nanoseconds\n",tu/10);
					System.out.printf("Transactional method deleted for     %d nanoseconds\n",td/10);
					break;
				}
				case "5": {
					System.exit(0);
					break;
				}
			}
		}

	}

	public void fillTables() {
		fillUserTable(20);
		fillProductTable(20);
		fillOrderTable(20);
	}

	public void fillUserTable(final int packageNumber) {
		UserDao userDao = (UserDao) context.getBean("userDao");
		System.out.println("Filling in user table...");
		for (int i = 0; i < packageNumber; i++) {
			userDao.insertBatch(dataCreator.generateUserList(PACKAGE_SIZE));
			System.out.printf("%d user records...%n", (i + 1) * PACKAGE_SIZE);
		}
		System.out.println("user table is done.");
	}

	public void fillProductTable(final int packageNumber) {
		ProductDao productDao = (ProductDao) context.getBean("productDao");
		System.out.println("Filling in product table...");
		for (int i = 0; i < packageNumber; i++) {
			productDao.insertBatch(dataCreator.generateProductList(PACKAGE_SIZE));
			System.out.printf("%d product records...%n", (i + 1) * PACKAGE_SIZE);
		}
		System.out.println("product table is done.");
	}

	public void fillOrderTable(final int packageNumber) {
		OrderDao orderDao = (OrderDao) context.getBean("orderDao");
		System.out.println("Filling in order table...");
		for (int i = 0; i < packageNumber; i++) {
			orderDao.insertBatch(dataCreator.generateOrderList(PACKAGE_SIZE));
			System.out.printf("%d order records...%n", (i + 1) * PACKAGE_SIZE);
		}
		System.out.println("order table is done.");
	}
}
