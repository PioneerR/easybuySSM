package com.easybuy.servlet.pre;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.easybuy.entity.Product;
import com.easybuy.service.cart.CartService;
import com.easybuy.service.cart.CartServiceImpl;
import com.easybuy.service.product.ProductService;
import com.easybuy.service.product.ProductServiceImpl;
import com.easybuy.servlet.web.AbstractServlet;
import com.easybuy.util.ReturnResult;
import com.easybuy.util.ShoppingCart;
import com.easybuy.util.ShoppingCartItem;

@WebServlet(urlPatterns={"/pre/CartServlet"},name="CartServlet")
public class CartServlet extends AbstractServlet {
	private ProductService productService;
	private CartService cartService;
	private static final Logger logger = Logger.getLogger(CartServlet.class);
	public void init(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-public.xml");
	/*	productService = new ProductServiceImpl();
		cartService = new CartServiceImpl();*/
		productService =(ProductService) ctx.getBean("productService");
		cartService = (CartService) ctx.getBean("cartService");
	}
	/**
	 * 获取购物车
	 * @param request
	 * @param response
	 * @return
	 */
	public ShoppingCart getShoppingCart(HttpServletRequest request,HttpServletResponse response){
		
		ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
		if(cart==null){
			cart = new ShoppingCart();
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	/**
	 * 添加商品到购物车
	 * @param request
	 * @param response
	 */
	public void addShoppingItem(HttpServletRequest request,HttpServletResponse response){
		String productId = request.getParameter("entityId");
		String productQuantity = request.getParameter("quantity");
		int quantity =1;
		if(productQuantity!=null){
			quantity = Integer.parseInt(productQuantity);
		}
logger.debug("调用addShoppingItem");
logger.debug("productId"+productId);
logger.debug("productQuantity"+productQuantity);
		if(productId!=null){
			int id = Integer.parseInt(productId);
			Product product = productService.getProductById(id);
logger.debug(product.getName());
			ShoppingCart cart = getShoppingCart(request, response);
			cart.addItem(product, quantity);
			cartService.calculate(cart);
			List<ShoppingCartItem>list = cart.items;
for(ShoppingCartItem item:list){
	System.out.println("商品"+item.getProduct().getName());
}				
		}
	}
	
	/**
	 * 查询购物车列表
	 * @param request
	 * @param response
	 * @return
	 */
	public List<ShoppingCartItem> queryProductList(HttpServletRequest request,HttpServletResponse response){
		ShoppingCart cart =getShoppingCart(request, response);
		List<ShoppingCartItem>list = cart.items;
String newjson = JSON.toJSONString(list);
logger.debug("newjson"+newjson);
		return list;
	}
	
	/**
	 * 跳转到结算页面
	 */
	public String toSettlement(HttpServletRequest request,HttpServletResponse response){	
		return "/pre/product/BuyCar";
	}
	
	public String toSettlement2(HttpServletRequest request,HttpServletResponse response){
		return "/pre/product/buycar_two";
	}
	/**
	 * 移除购物车产品
	 * @param request
	 * @param response
	 * @return
	 */
	public List<ShoppingCartItem> removeShoppingCartItem(HttpServletRequest request,HttpServletResponse response){
		String entityId =request.getParameter("id");
	logger.debug("进行删除的商品id"+entityId);
		ShoppingCart cart =getShoppingCart(request, response);
		if(entityId!=null){
			int delId =Integer.parseInt(entityId);
			cartService.removeShoppingItem(cart, delId);
		}
		return cart.items;	
	}
	
	/**
	 * 查询购物车产品信息
	 * @param request
	 * @param response
	 * @return
	 */


	public List<ShoppingCartItem> queryShoppingCartItem(HttpServletRequest request,HttpServletResponse response){
		
		ShoppingCart cart = getShoppingCart(request, response);
		return cart.items;				
	}
	/**
	 * 修改商品订单数量
	 * @param request
	 * @param response
	 * @return
	 */
	public Double modifyShoppingCartItem(HttpServletRequest request,HttpServletResponse response){
		String quantityStr = request.getParameter("quantity");
		String entityIdStr = request.getParameter("entityId");
		ShoppingCart cart = getShoppingCart(request, response);
		int entityId =0;
		if(entityIdStr!=null){
			entityId = Integer.parseInt(entityIdStr);
		}
		double sum =cart.getSum();
		if(quantityStr!=null){
			int quantity = Integer.parseInt(quantityStr);
			for(ShoppingCartItem item:cart.items){
				if(item.getProduct().getId()==entityId){
					item.setQuantity(quantity);
	logger.debug("修改产品"+item.getProduct().getName()+"的数量为"+item.getQuantity());
				}
			}
			sum =cartService.calculate(cart).getSum();
	logger.debug("修改商品数量后的购物总价"+sum);
		}
		return sum;
	}
	/**
	 * 清空购物车
	 * @param request
	 * @param response
	 * @return
	 */
	public ReturnResult clearCart(HttpServletRequest request,HttpServletResponse response){
		request.getSession().removeAttribute("cart");
		ReturnResult result = new ReturnResult();
		result.returnSuccess(null);
		return result;
	}
	/**
	 * 返回反射类对象
	 */
	@Override
	public Class getServletClass() {
		return CartServlet.class;
	}
	
}
