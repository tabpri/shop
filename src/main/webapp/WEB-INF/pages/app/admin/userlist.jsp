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
<link rel="stylesheet" type="text/css" href="/Shop/contents.css">

<tiles:insert definition=".layout">
	<tiles:put name="title" value="ショッピングカートのユーザー" direct="true" />
	<tiles:put name="content" direct="true">
	<display:table name="${userlist}" id="row" 
                            requestURIcontext="false"
                            export="false" pagesize="20" > 
		<display:column media="html" title="ユーザー名" property="name">
		</display:column>
		<display:column media="html" title="Eメール" property="email">
		</display:column>
		<display:column media="html" title="編集" >
			<a href="User.do?action=showUser&id=${row.id}">編集</a>
		</display:column>
		<display:column media="html" title="削除" >
			<a onClick="javascript:if(window.confirm('削除してもよろしいですか？')){location.href='User.do?action=deleteUser&id=${row.id}';}" ><img src="/Shop/images/icons/bt_delete.gif"></a>
		</display:column>
	</display:table>
	
	<div id="useradd" >
			&nbsp;&nbsp;<a href="User.do?action=showUser">ショッピングカートのオペレータを追加</a>
	</div>
		
	</tiles:put>
</tiles:insert>