package com.easybuy.service.news;



public class NewsFactory {

	private NewsFactory() {
	}

	private static NewsService service = null;

	static {
		service = new NewsServiceImpl();
	}

	public static NewsService getService() {
		return service;
	}

	
}