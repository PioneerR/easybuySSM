<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="${ctx}/statics/js/common/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
    var contextPath = "${ctx}";
</script>
<div class="nav">
    <div class="nav_t">全部商品分类</div>
    <div class="leftNav none" style="display: none;">
        <ul id="leftMenu">
            <c:forEach items="${vo1List}" var="temp">
                <li>
                    <div class="fj">
                        <span class="n_img"><span></span>
                            <%-- <img src="${ctx}/statics/images/${temp.category.iconClass}"/> --%></span>
                        <span class="fl">${temp.category.name}</span>
                    </div>
                    <div class="zj">
                        <div class="zj_l">
                            <c:forEach items="${temp.categoryVoList}" var="vo">
                                <div class="zj_l_c">
                                    <h2>
                                        <a href="${ctx}/pre/productServlet?action=index&categoryLevelId=${vo.category.id}">${vo.category.name}</a>
                                    </h2>
                                    <c:forEach items="${vo.categoryVoList}" var="vo2">
                                        <a href="${ctx}/pre/productServlet?action=index&categoryLevelId=${vo2.category.id}">${vo2.category.name}</a>
                                    </c:forEach>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<ul class="menu_r">
    <li><a href="${ctx}/home?action=index">首页</a></li>
    <c:forEach items="${vo1List}" var="temp">
        <li><a href="${ctx}/pre/productServlet?action=index&categoryLevelId=${temp.category.id}">${temp.category.name}</a></li>
    </c:forEach>
</ul>
<div class="m_ad">中秋送好礼！</div>