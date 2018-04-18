package com.easybuy.service.product;


public class ProductFactory {

	private ProductFactory() {
	}

	private static ProductService service = null;
	static {
		service = new ProductServiceImpl();
	}

	public static ProductService getService() {
		return service;
	}
}