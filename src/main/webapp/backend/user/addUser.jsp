<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/common/pre/header.jsp" %>
    <script src="${ctx}/statics/js/backend/backend.js"></script>
</head>
<body>
<%@ include file="/common/backend/searchBar.jsp" %>
<!--End Header End-->
<div class="i_bg bg_color">
    <!--Begin 用户中心 Begin -->
    <div class="m_content">
        <%@ include file="/common/backend/leftBar.jsp" %>
        <div class="m_right" id="content">
            <div class="mem_tit">
                <c:choose>
                    <c:when test="${empty user.id || user.id==0}">
                        添加用户
                    </c:when>
                    <c:otherwise>
                        修改用户
                    </c:otherwise>
                </c:choose>
            </div>
            <br>
            <form action="${ctx}/backend/userServlet?action=addUser" method="post" id="userAdd" onsubmit="return checkUser();">
                <table border="0" class="add_tab" style="width:930px;" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="135" align="right">用户姓名</td>
                        <td colspan="3" style="font-family:'宋体';">
                            <input type="text" value="${user.loginName}" class="add_ipt" name="loginName" pattern="[a-zA-Z]\w{3,15}" required/>
                            <input type="hidden" value="${user.id}" name="id">
                        </td>
                    </tr>
                    <tr>
                        <td width="135" align="right">真实姓名</td>
                        <td>
                            <input type="text" value="${user.userName}" class="add_ipt" name="userName" />
                        </td>
                    </tr>
                    <c:if test="${empty user.id ||  user.id==0}">
                        <tr>
                            <td width="135" align="right">密码</td>
                            <td>
                                <input type="password" value="" class="add_ipt" name="password" 
                                	pattern="^(?![A-Z]+$)(?![a-z]+$)(?!\d+$)(?![\W_]+$)\S{6,16}$" required/>
                            </td>
                        </tr>
                        <tr>
                            <td width="135" align="right">确认密码</td>
                            <td>
                                <input type="password" value="" class="add_ipt" name="repPassword" 
                                	pattern="^(?![A-Z]+$)(?![a-z]+$)(?!\d+$)(?![\W_]+$)\S{6,16}$" required/>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td width="135" align="right">身份证号</td>
                        <td>
                            <input type="text" value="${user.identityCode}" class="add_ipt" name="identityCode"
                                   id="identityCode"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="135" align="right">邮箱</td>
                        <td>
                            <input type="text" value="${user.email}" class="add_ipt" name="email" id="email" 
                            	pattern="^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$" required/>
                        </td>
                    </tr>
                    <tr>
                        <td width="135" align="right">手机</td>
                        <td>
                            <input type="text" value="${user.phone}" class="add_ipt" name="phone" id="phone" pattern="1[0-9]{10}" required/>
                        </td>
                    </tr>
                    <tr>
                        <td width="135" align="right">用户类型</td>
                        <td>
                            <select name="type">
                                <option value="1" <c:if test="${user.type==1}">selected="selected"</c:if>>管理员</option>
                                <option value="0" <c:if test="${user.type==3}">selected="selected"</c:if>>普通用户</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <c:choose>
                                <c:when test="${empty user.id || user.id==0}">
                                    <input type="button" value="添加用户" class="s_btn" onclick="toAddUser();">
                                </c:when>                                
                                <c:otherwise>
                                    <input type="button" value="修改信息" class="s_btn"  onclick="toAddUser();">
                                </c:otherwise>
                            </c:choose>
                        </td>                        
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <%@ include file="/common/pre/footer.jsp" %>
</div>
</body>
</html>


