package com.becomejavasenior.controller;

import com.becomejavasenior.dao.OrderDao;
import com.becomejavasenior.dao.ProductDao;
import com.becomejavasenior.dao.UserDao;
import com.becomejavasenior.model.DataCreator;
import com.becomejavasenior.service.*;
import com.becomejavasenior.service.general.GeneralService;
import com.becomejavasenior.service.general.GeneralServiceImpl;
import com.becomejavasenior.service.general.GeneralServiceTransImpl;
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
				case "3":{

					GeneralService productService = (GeneralServiceImpl)appGenerator.context.getBean("productService");
					GeneralService userService = (GeneralServiceImpl)appGenerator.context.getBean("userService");
					GeneralService orderService = (GeneralServiceImpl)appGenerator.context.getBean("orderService");

					GeneralServiceTransImpl productTransService = (GeneralServiceTransImpl)appGenerator.context.getBean("productTransService");
					GeneralServiceTransImpl userTransService = (GeneralServiceTransImpl)appGenerator.context.getBean("userTransService");
					GeneralServiceTransImpl orderTransService = (GeneralServiceTransImpl)appGenerator.context.getBean("orderTransService");

					for(int i = 0; i<2; i++) {

						System.out.println("Non-transactional operation");

						long startTime = System.nanoTime();
						for (int j = 0; j<50; j++) {
							productService.operationGroup(productService.getEntity1000().get((int) Math.random()));
							orderService.operationGroup(orderService.getEntity1000().get((int) Math.random()));
							userService.operationGroup(userService.getEntity1000().get((int) Math.random()));
							System.out.print(".");
						}
						System.out.println("Spent time = " + (System.nanoTime() - startTime) / 1000000000.0 + " s");

						System.out.println();
						System.out.println("Transactional operation with propagation.REQUIRED");

						startTime = System.nanoTime();
						for(int j = 0; j<50;j++) {
							productTransService.operationGroup(productTransService.getEntity1000().get((int) Math.random()));
							orderTransService.operationGroup(orderTransService.getEntity1000().get((int) Math.random()));
							userTransService.operationGroup(userTransService.getEntity1000().get((int) Math.random()));
							System.out.print(".");
						}
						System.out.println("Spent time = " + (System.nanoTime() - startTime) / 1000000000.0 + " s");


						System.out.println();
						System.out.println("Transactional operation with propagation.REQUIRES_NEW");

						startTime = System.nanoTime();
						for(int j = 0; j<50;j++) {
							productTransService.operationGroupReqNew(productTransService.getEntity1000ReqNew().get((int) Math.random()));
							orderTransService.operationGroupReqNew(orderTransService.getEntity1000ReqNew().get((int) Math.random()));
							userTransService.operationGroupReqNew(userTransService.getEntity1000ReqNew().get((int) Math.random()));
							System.out.print(".");
						}
						System.out.println("Spent time = " + (System.nanoTime() - startTime) / 1000000000.0 + " s");


						System.out.println();
						System.out.println("Transactional operation with propagation.NOT_SUPPORTED");

						startTime = System.nanoTime();
						for(int j = 0; j<50;j++) {
							productTransService.operationGroupNotSup(productTransService.getEntity1000NotSup().get((int) Math.random()));
							orderTransService.operationGroupNotSup(orderTransService.getEntity1000NotSup().get((int) Math.random()));
							userTransService.operationGroupNotSup(userTransService.getEntity1000NotSup().get((int) Math.random()));
							System.out.print(".");
						}
						System.out.println("Spent time = " + (System.nanoTime() - startTime) / 1000000000.0 + " s");
						System.out.println("============================================================");
					}
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
