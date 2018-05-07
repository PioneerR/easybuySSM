<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/common/pre/header.jsp" %>
    <script type="text/javascript" src="${ctx}/statics/js/common/n_nav.js"></script>
    <script src="${ctx}/statics/js/cart/cart.js"></script>
    <title>易买网</title>
</head>
<body>
<!--Begin Header Begin-->
<div id="searchBar">
    <%@ include file="/common/pre/searchBar.jsp" %>
</div>
<!--End Header End-->
<!--Begin Menu Begin-->
<div class="menu_bg">
    <div class="menu">
        <!--Begin 商品分类详情 Begin-->
        <%@ include file="/common/pre/categoryBar.jsp" %>
        <!--End 商品分类详情 End-->
    </div>
</div>
<!--End Menu End-->
<div class="i_bg">
    <!--Begin 筛选条件 Begin-->
    <!--End 筛选条件 End-->
    <div class="content mar_20">
        <div id="favoriteList">
        </div>
        <div class="l_list">
            <div class="list_t">
                <span class="fr">共发现${total}件</span>
            </div>
            <div class="list_c">
                <ul class="cate_list">
                    <c:forEach items="${productList}" var="temp">
                        <li>
                            <div class="img">
                                <a href="${ctx}/pre/productServlet?action=getProductDetail&id=${temp.id}" target="_blank">
                                    <img src="${ctx}/files/${temp.fileName}" width="210" height="185"/>
                                </a>
                            </div>
                            <div class="price">
                                <font>￥<span>${temp.price}</span></font>
                            </div>
                            <div class="name"><a href="${ctx}/pre/productServlet?action=getProductDetail&id=${temp.id}">${temp.name}</a></div>
                            <div class="carbg">
                                <a href="javascript:void(0);" class="ss" onclick="addFavor('${temp.id}','${sessionScope.loginUser.id}')">收藏</a>
                                <a href="javascript:void(0);" class="j_car" onclick="addShoppingCartItemByParam('${temp.id}',1);">加入购物车</a>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
                <%-- <%@ include file="/common/pre/pagerBar.jsp" %> --%>
                
                
                <div class="pages">
				    <c:if test="${page.totalPageCount>=1}">
				      <a href="${ctx}/${page.url}&currentPageNo=1&categoryLevelId=${categoryId}&proName=${proName}" class="p_pre">首页</a>
				        <c:if test="${page.currentPageNo>1}">
				            <a href="${ctx}/${page.url}&currentPageNo=${page.currentPageNo-1}&categoryLevelId=${categoryId}&proName=${proName}" class="p_pre">上一页</a>
				        </c:if>
				        <!-- 三目运算符的嵌套 -->
				        <c:forEach  var="temp" begin="${(page.currentPageNo-page.currentPageNo%10)==page.currentPageNo?
				        (page.currentPageNo%10+1):(page.currentPageNo-page.currentPageNo%10)+1}" 
				        	end="${(page.currentPageNo-page.currentPageNo%10)==page.currentPageNo?
				        		page.currentPageNo:(        		
				        		(page.currentPageNo-page.currentPageNo%10)+10>page.totalPageCount?
				        		page.totalPageCount:page.currentPageNo-page.currentPageNo%10+10      		
				        		)}" 
				        	step="1">
				            <c:if test="${page.currentPageNo==temp}">
				                <a href="${ctx}/${page.url}&currentPageNo=${temp}&categoryLevelId=${categoryId}&proName=${proName}" class="cur">${temp}</a>
				            </c:if>
				            <c:if test="${page.currentPageNo!=temp}">
				                <a href="${ctx}/${page.url}&currentPageNo=${temp}&categoryLevelId=${categoryId}&proName=${proName}">${temp}</a>
				            </c:if>
				        </c:forEach>
				        
				      <c:if test="${page.currentPageNo<page.totalPageCount}">
				         <a href="${ctx}/${page.url}&currentPageNo=${page.currentPageNo+1}&categoryLevelId=${categoryId}&proName=${proName}" class="p_pre">下一页</a>
				      </c:if>
				      <a href="${ctx}/${page.url}&currentPageNo=${page.totalPageCount}&categoryLevelId=${categoryId}&proName=${proName}" class="p_pre">尾页</a>
				    </c:if>
				    <c:if test="${page.totalPageCount==0}">
				        	暂无记录
				    </c:if>
				</div>
                                
                
            </div>
        </div>
    </div>

 <script type ="text/javascript">
 favoriteList(${sessionScope.loginUser.id});
	function addShoppingCartItemByParam(entityId,quantity){
	    $.ajax({
	        "url":"CartServlet",
	        "data":{
	            "action":"addShoppingItem",
	            "entityId":entityId,
	            "quantity":quantity
	        },
	        "dataType":"json",
	        "method":"post",
	        "success":function(data){
	        	 alert("已添加到购物车");
	        	queryShoppingCart();
	        		 
	        }
	    }); 
     //$.post("CartServlet","action=addShoppingItem&entityId="+entityId+"&quantit="+quantity,success,"text");
	};
	
	function queryShoppingCart(){
		$.ajax({
				"url":"CartServlet",
				"type":"get",
				"data":{
					"action":"queryShoppingCartItem"
				},
				"dataType":"json",
				"success":function(data){
					/* alert(data); */
					var sum  = 0;
					var itemCount = data.length;
					/* alert(itemCount); */
					if(data.length>0){
						$(".car_t").html("购物车["+itemCount+"]");
					}else{
						$(".car_t").html("购物车[空]");
					};
					$(".cars").empty();
						$(data).each(function(){
						sum = sum+this.cost;
						$(".cars").append(
						"<li>"
	                      + "<div class=\"img\"><a href=\"easybuySSM/pre/productServlet?action=getProductDetail&id="+this.product.id+"\"><img src=\"../files/"+this.product.fileName+"\" width=\"58\" height=\"58\" /></a></div>" 
	                      +"<div class=\"name\"><a href=\"easybuySSM/pre/productServlet?action=getProductDetail&id="+this.product.id+"\">"+this.product.name+"</a></div>"  
	                      + "<div class=\"price\"><font color=\"#ff4e00\">￥"+this.product.price+"</font> X"+this.quantity+"</div>"
	                    +"</li>");
						});	
						$(".price_sum").find("span").html(sum);		
				}		
			});
	};


</script>
        
    <%@ include file="/common/pre/footer.jsp" %>
</div>
</body>
</html>
