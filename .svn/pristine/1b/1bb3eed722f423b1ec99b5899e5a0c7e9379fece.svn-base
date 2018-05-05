package com.easybuy.servlet.pre;

import com.easybuy.entity.News;
import com.easybuy.entity.Product;
import com.easybuy.service.category.CategoryService;
import com.easybuy.service.news.NewsService;
import com.easybuy.service.product.ProductService;
import com.easybuy.servlet.web.AbstractServlet;
import com.easybuy.util.CategoryVo;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

@WebServlet("/home")
public class HomeServlet extends AbstractServlet {

	private ProductService productService;
	private CategoryService categoryService;
	private NewsService newsService;
	
	public void init() throws ServletException {
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();			
		this.productService=(ProductService)context.getBean("productService");
		this.categoryService=(CategoryService)context.getBean("categoryService");
		this.newsService=(NewsService) context.getBean("newsService");	
	}

	public Class getServletClass() {
		return HomeServlet.class;
	}

	public String index(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		List<CategoryVo> vo1List=categoryService.getListAllCategory();
		
		//插入5条资讯
		List<News> listAllNews=newsService.getAllNews();
		List<News> list5News=listAllNews.subList(0, 5);
		request.setAttribute("news", list5News);
		
		//查询一楼
        for (CategoryVo vo : vo1List) {
            List<Product> productList = productService.getListProduct(
            			null, vo.getCategory().getId(), 0, 8);
            vo.setProductList(productList);
        }
        //封装返回
        request.setAttribute("vo1List", vo1List);	
		return "index";
	}

	


}
