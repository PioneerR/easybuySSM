package com.easybuy.service.product;

import com.easybuy.dao.product.ProductMapper;
import com.easybuy.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	private List<Product> list=new ArrayList();
	boolean flag=false;
	private Logger log=Logger.getLogger(ProductServiceImpl.class);
	
	@Autowired(required=false)
	private ProductMapper productMapper=null;
	
	private Object obj=null;
	
	@Override
	public boolean addProduct(Product p) {
		
		
		
		return false;
	}

	@Override
	public boolean deleteProductById(int id) {
		
		
		
		return false;
	}

	@Override
	public boolean updateProduct(Product p) {
		
		
		
		return false;
	}

	@Override
	public Product getProductById(Integer id) {
		
		
		
		return null;
	}

	@Override
	public int getProductCountByCategoryId(Integer categoryId) {
		int count=0;
		try {
			count=productMapper.getProductCountByCategoryId(categoryId);
		} catch (Exception e) {
			log.error(e);
		}
		return count;
	}

	@Override
	public int getTotalCount() {
		int count=0;
		try {
			count=productMapper.getTotalCount();
		} catch (SQLException e) {
			log.error(e);
		}
		return count;
	}
	
	@Override
	public List<Product> getListProductByCategoryLevelId(Integer categoryLevelId,Integer startIndex, Integer pageSize) {
		try {
			list=productMapper.getListProductByCategoryLevelId(categoryLevelId,startIndex, pageSize);
		} catch (SQLException e) {
			log.error(e);
		}
		return list;
	}

	
	
	
	
}
