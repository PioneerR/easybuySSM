<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
    var contextPath = "${ctx}";
</script> 

<div class="pages">
    <c:if test="${page.totalPageCount>=1}">
      <a href="${ctx}/${page.url}&currentPageNo=1" class="p_pre">首页</a>
        <c:if test="${page.currentPageNo>1}">
            <a href="${ctx}/${page.url}&currentPageNo=${page.currentPageNo-1}" class="p_pre">上一页</a>
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
                <a href="${ctx}/${page.url}&currentPageNo=${temp}" class="cur">${temp}</a>
            </c:if>
            <c:if test="${page.currentPageNo!=temp}">
                <a href="${ctx}/${page.url}&currentPageNo=${temp}">${temp}</a>
            </c:if>
        </c:forEach>
        
      <c:if test="${page.currentPageNo<page.totalPageCount}">
         <a href="${ctx}/${page.url}&currentPageNo=${page.currentPageNo+1}" class="p_pre">下一页</a>
      </c:if>
      <a href="${ctx}/${page.url}&currentPageNo=${page.totalPageCount}" class="p_pre">尾页</a>
    </c:if>
    <c:if test="${page.totalPageCount==0}">
        暂无记录
    </c:if>
</div>
