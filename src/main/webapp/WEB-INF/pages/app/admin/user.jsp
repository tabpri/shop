<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="net.malta.model.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<%@ page isELIgnored="false" %>
<link rel="stylesheet" type="text/css" href="/shop/contents.css">

<tiles:insert definition=".layout">

	<tiles:put name="title" value="商品登録" direct="true" />
	<tiles:put name="content" direct="true">
		<html:form method="POST" action="User.do?action=saveUser" >
		
		
	<div id="errors" >
		<font color="red">
			<html:errors/>
		</font>	
	</div>
		
			<html:hidden property="id" />
			<table class="formArea.shortForm" style="width:300px;">
					<tr>
						<td>
							名
						</td>
						<td >
							<html:text property="name"  maxlength="100" />			
						</td>
					</tr>			
					<tr>
						<td>
							Eメール
						</td>
						<td>
							<html:text property="email"  maxlength="100" />							
						</td>
					</tr>
					<tr>
						<td>
							パスワード
						</td>
						<td>
							<html:password property="password" maxlength="20" />													
						</td>
					</tr>
					<tr>
						<td colspan="2" align="right">
							<html:submit value="提出します"/>&nbsp;&nbsp;&nbsp;
							<html:reset value="リセット"/>&nbsp;&nbsp;&nbsp;
																				
						</td>
					</tr>
					
			</table>			
		</html:form>			
	</tiles:put>
	
</tiles:insert>