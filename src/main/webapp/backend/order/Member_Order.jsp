<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link type="text/css" rel="stylesheet" href="${ctx}/statics/css/style.css" />   
	    <%@ include file="/common/pre/header.jsp" %>
		<title>易买网</title>
	</head>
	<body>  
	<!--Begin Header Begin-->
		 <%@ include file="/common/pre/searchBar.jsp" %>
	<!--End Header End--> 
	<div class="i_bg bg_color">
	    <!--Begin 用户中心 Begin -->
		<div class="m_content">
	   		<div class="m_left">
	        	<div class="left_n">管理中心</div>
	            <div class="left_m">
	            	<div class="left_m_t t_bg1">订单中心</div>
	                <ul>
	                	<li><a href="Member_Order.html" class="now">我的订单</a></li>
	                    <li><a href="Member_Address.html">收货地址</a></li>                    
	                </ul>
	            </div>
	            <div class="left_m">
	            	<div class="left_m_t t_bg2">会员中心</div>
	                <ul>
	                	<li><a href="Member_User.html">用户信息</a></li>
	                    <li><a href="Member_Collect.html">我的收藏</a></li>
	                </ul>
	            </div>
	        </div>
			<div class="m_right">
	            <p></p>
	            <div class="mem_tit">我的订单</div>
	            <table border="0" class="order_tab" style="width:930px; text-align:center; margin-bottom:30px;" cellspacing="0" cellpadding="0">
	              <tr>                                                                                                                                                    
	                <td width="30%">订单号</td>
	                <td width="20%">下单时间</td>
	                <td width="15%">订单总金额</td>
	                <td width="15%">订单状态</td>
	                <td width="20%">操作</td>
	              </tr>
	              <c:forEach items="${requestScope.orderList}" var="temp">
	              <tr>
	                <td><font color="#ff4e00">${temp.serialNumber}</font></td>
	                <td>
	                <fmt:formatDate value="${temp.createTime}" 
	                	pattern="yyyy-MM-dd hh:mm:ss" type="date" dateStyle="long"/>
	                </td>
	                <td>￥${temp.cost}</td>
	                <td>
	                	<c:if test="${temp.status==1}">
	                	未确认
	                	</c:if>
	                	<c:if test="${temp.status==2}">
	                	已确认
	                	</c:if>
	                	<c:if test="${temp.status==3}">
	                	配货中
	                	</c:if>
	                	<c:if test="${temp.status==4}">
	                	卖家已发货
	                	</c:if>
	                	<c:if test="${temp.status==5}">
	                	已收货
	                	</c:if>
	                	<c:if test="${temp.status==6}">
	                	已取消
	                	</c:if>
	        
	                </td>
	                <td>
	                	<c:if test="${temp.status==1}"><a href="javascript:void(0)" onclick="Confirm(${temp.id},jq(this));">确认订单</a></c:if>
	                	<c:if test="${temp.status==2 or temp.status==3 or temp.status==4 or temp.status==5}"><a href="javascript:void(0)" onclick="cancelOrder(${temp.id},jq(this))">取消订单</a></c:if>
	                	<c:if test="${temp.status==6}">已取消</c:if>
	                </td>
	              </tr>
	              </c:forEach>              
	            </table>
	
	    </div>
		<!--End 用户中心 End--> 
	    <!--Begin Footer Begin -->
	     <%@ include file="/common/pre/footer.jsp" %>
	    <!--End Footer End -->    
	</div>
		<script type="text/javascript">
			function Confirm(orderId,cur){
			/* alert(orderId); */
				jq.ajax({
					"url":"/easybuySSM/pre/OrderServlet",
					"type":"post",
					"data":{
						"action":"updateOrder",
						"orderId":orderId,
						"status":"2"
					},
					"dataType":"json",
					"success":function(data){
						alert("状态修改成功");
						jq(cur).parent().prev().html("已确认");
						jq(cur).parent().append("<a href=\"javascript:void(0)\" onclick=\"cancelOrder(${temp.id},jq(this))\">取消订单</a>");
						
					}	
				});
			}
			
			function cancelOrder(orderId,cur){
			/* alert(orderId); */
				jq.ajax({
					"url":"/easybuySSM/pre/OrderServlet",
					"type":"post",
					"data":{
						"action":"updateOrder",
						"orderId":orderId,
						"status":"6"
					},
					"dataType":"json",
					"success":function(data){
						alert("状态修改成功");
						jq(cur).parent().prev().html("已取消");
						jq(cur).html("重新确认");	
					}	
				});
			}
		</script>
	</body>
</html>

