package com.easybuy.service.product;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.easybuy.entity.Product;

public class ProductServiceTest {
	private static final Logger logger = Logger.getLogger(ProductServiceTest.class);
	@Test
	public void test(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-public.xml");
		ProductService productService = (ProductService) ctx.getBean("productService");
		//List<Product>list =productService.getAllProduct();
		//for(Product p:list){
		//	logger.info("product"+p.getName());
		//}
	}
}
