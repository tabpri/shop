<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="http://www.enclosing.net/tags/storyteller" prefix="st" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ page isELIgnored="false" %>
<%@ page import="net.malta.web.utils.MainItem,net.malta.web.utils.ActionUtil" %>

<script src="ts_picker.js"></script>
<tiles:insert definition=".layout">

	<tiles:put name="title" value="商品登録" direct="true" />

	<tiles:put name="content" direct="true">

	<form method="POST" action="UpdatePaymentStatus.do">
					<input type="hidden" name="purchaseId" value="<c:out value="${param.id}"/>" />
	
				<table>
					<tr>
						<td>支払い状況への更新:</td>
						<td><b>COMPLETED</b></td>
					</tr>
					<tr>
						<td>トランザクションリファレンス：</td>
						<td><input type='text' name='transactionReference' /></td>
					</tr>
					<tr>
						<td>取引日：</td>
						<td><input type='text' name='transactionDateS' readonly="readonly"/>
						<a href="javascript:show_calendar('document.forms[0].transactionDateS', document.forms[0].transactionDateS.value);">
<img src="cal.gif" width="16" height="16" border="0"></a></td>
					</tr>
					<tr>
						<td colspan='2' align="right">
							<html:reset value="リセット"/>						
							<input name="submit" type="submit" value="支払いを完了する" />							
						</td>
					</tr>
				</table>
	</form>
	</tiles:put>
</tiles:insert>