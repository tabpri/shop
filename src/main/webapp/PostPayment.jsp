<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ page import="net.malta.model.Purchase"%>
<%@ page import="net.malta.model.PublicUser"%>
<%@ page import="net.malta.model.Choise"%>
<%@page import="java.util.Date"%>


<%
  session.invalidate();
  // セッションの初期化と生成
  session = request.getSession(true);

  // Purchase生成
  Purchase purchase = Purchase.Factory.newInstance();
  purchase.setId(new Integer(25));

  PublicUser publicUser = PublicUser.Factory.newInstance();
  publicUser.setId(new Integer(2));

  // ダミー生成
  purchase.setPublicUser(publicUser);

  session.setAttribute("u", publicUser);
  session.setAttribute("purchase", purchase);

 %>

<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.0/jquery.min.js"></script>
<style>
  .card{background-color : #ADD8E6}
  .menber{background-color : #FFB6C1}
</style>
</head>
<body>
  <form name="POSTFORM" id="form1" method="POST" action="/Shop/PostPurchaseVPChoosingPaymentMethod.do">
    <p style="font-size: 20px; font-weight: bold; text-align: center;">PostPurchaseVPChoosingPaymentMethodアクションにポストする</p>
    <div align="center">
    <input type="button" value="クレジットカードを利用する決済" name="card" id="cardbutton" disabled="true">
    <input type="button" value="カード会員番号を利用する決済" name="manber" id="menberbutton">
    <input type="submit" name="send" id="send" value="送信(POST)" style="width: 150px; height: 30px;">
    </div><br>
    <table border="1" align="center" style="font-size: 10px;">
      <tr style="background-color: gray;"><td>必須</td><td>項目名</td><td>変数名</td><td>型</td><td>値</td><td>備考</td><tr>
      <!-- カード決済 -->
      <tr id="cardNo" class="card"><td>○</td><td>カード番号</td><td>cardNo</td><td>String</td><td><input type="text" name="cardNo" value="4111111111111111"></td><td>4999000000000012 取扱不可<br>4123450131003312 ACS利用（12345で成功</td></tr>
      <tr id="expire" class="card"><td>○</td><td>有効期限</td><td>expire</td><td>String</td><td><input type="text" name="expire" value="1705"></td><td></td></tr>
      <tr id="security" class="card"><td>  </td><td>セキュリティーコード</td><td>securityCode</td><td>String</td><td><input type="text" name="securityCode" value="123"></td><td></td></tr>
      <!-- 会員カード決済 -->
      <tr id="siteId" class="menber"></tr>
      <tr id="sitePass" class="menber"></tr>
      <tr id="memberId" class="menber"></tr>
      <tr id="seqMode" class="menber"></tr>
      <tr id="cardSeq" class="menber"></tr>
      <tr id="cardPass" class="menber"></tr>

      <tr style="background-color: gray;"><td>○</td><td>サイトID</td><td>siteId</td><td>String</td><td>tsite00019730</td><td>固定値</td></tr>
      <tr style="background-color: gray;"><td>○</td><td>サイトパスワード</td><td>sitePass</td><td>String</td><td>urxthwt2</td><td>固定値</td></tr>
      <tr style="background-color: gray;"><td>○</td><td>ショップID</td><td>shopId</td><td>String</td><td>tshop00021241</td><td>固定値</td></tr>
      <tr style="background-color: gray;"><td>○</td><td>ショップパスワード</td><td>shopPass</td><td>String</td><td>7dgkrxz1</td><td>固定値</td></tr>
      <tr style="background-color: gray;"><td>○</td><td>オーダーID</td><td>orderId</td><td>String</td><td>SHOP-TEST000001</td><td>元の処理と一緒</td></tr>
      <tr style="background-color: gray;"><td>○</td><td>処理区分</td><td>jobCd</td><td>String</td><td>CAPTURE</td><td>固定値 CAPTURE:即時売上</td></tr>
      <tr style="background-color: gray;"><td>  </td><td>商品コード</td><td>itemCode</td><td>String</td><td>0000990</td><td>省略</td></tr>
      <tr style="background-color: gray;"><td>▲</td><td>利用金額</td><td>amount</td><td>Integer</td><td>Purchase.getTotal()</td><td>Purchaseから取得</td></tr>
      <tr style="background-color: gray;"><td>  </td><td>税送料</td><td>tax</td><td>Integer</td><td></td><td>省略</td></tr>
      <tr style="background-color: gray;"><td>  </td><td>セキュア使用フラグ</td><td>tdFlag</td><td>String</td><td>1</td><td>固定値 1:行う</td></tr>
      <tr style="background-color: gray;"><td>  </td><td>3D セキュア表示店舗名</td><td>tdTenantName</td><td>String</td><td></td><td>省略</td></tr>
      <tr style="background-color: gray;"><td>○</td><td>取引ID</td><td>accessId</td><td>String</td><td></td><td>省略</td></tr>
      <tr style="background-color: gray;"><td>○</td><td>取引パスワード</td><td>accessPass</td><td>String</td><td></td><td>省略</td></tr>
      <tr style="background-color: gray;"><td>▲</td><td>支払方法</td><td>method</td><td>String</td><td>1</td><td>固定値 1:一括</td></tr>
      <tr style="background-color: gray;"><td>▲</td><td>支払回数</td><td>payTimes</td><td>Integer</td><td></td><td>省略</td></tr>
      <tr style="background-color: gray;"><td>  </td><td>加盟店自由項目 1</td><td>clientField1</td><td>String</td><td></td><td>省略</td></tr>
      <tr style="background-color: gray;"><td>  </td><td>加盟店自由項目 2</td><td>clientField2</td><td>String</td><td></td><td>省略</td></tr>
      <tr style="background-color: gray;"><td>  </td><td>加盟店自由項目 3</td><td>clientField3</td><td>String</td><td></td><td>省略</td></tr>
      <tr style="background-color: gray;"><td>  </td><td>加盟店自由項目返却フラグ</td><td>clientFieldFlag</td><td>String</td><td></td><td>省略 1:返却する(デフォルト)</td></tr>
      <tr style="background-color: gray;"><td>○</td><td>使用端末情報</td><td>deviceCategory</td><td>String</td><td></td><td>省略 0:PC(デフォルト)</td></tr>
      <tr style="background-color: gray;"><td>○</td><td>結果受取用URL</td><td>termUrl</td><td>String</td><td>req.getRequestURL().toString()</td><td>本人認証結果を受け取るためのURL<br>MDとPaResの項目がPOSTされるので受け取って認証後決済</td></tr>
    </table>
  <input type="hidden" name="paymentMethod" value="1">
  <input type="hidden" name="deliverymethod" value="1">
  </form>
</body>
<script>

    $('#cardbutton').click(function () {
    	$(this).prop("disabled", true);
    	$('#menberbutton').prop("disabled", false);
    	$('#memberId').empty();
    	$('#seqMode').empty();
    	$('#cardSeq').empty();
    	$('#cardPass').empty();
    	if($('#cardNo').children('td').length == 0) {
    		$('#cardNo').append('<td>○</td><td>カード番号</td><td>cardNo</td><td>String</td><td><input type="text" name="cardNo" value="4111111111111111"></td><td>4999000000000012 取扱不可<br>4123450131003312 ACS利用（12345で成功</td>');
    		$('#expire').append('<td>○</td><td>有効期限</td><td>expire</td><td>String</td><td><input type="text" name="expire" value="1705"></td><td></td>');
    		$('#security').append('<td>  </td><td>セキュリティーコード</td><td>securityCode</td><td>String</td><td><input type="text" name="securityCode" value="123"></td><td></td>');
    	}
    });

    $('#menberbutton').click(function () {
    	$(this).prop("disabled", true);
    	$('#cardbutton').prop("disabled", false);
    	$('#cardNo').empty();
    	$('#expire').empty();
    	$('#security').empty();
    	if($('#siteId').children('td').length == 0) {
    		$('#memberId').append('<td>○</td><td>会員ID</td><td>memberId</td><td>String</td><td><input type="text" name="memberId" value="1111122222333334444455555"></td><td></td>');
    		$('#seqMode').append('<td>  </td><td>カード登録連番モード</td><td>seqMode</td><td>String</td><td><input type="text" name="seqMode" value="1"></td><td>以下のいずれかを設定 0:論理モード(デフォルト) 1:物理モード</td>');
    		$('#cardSeq').append('<td>○</td><td>カード登録連番</td><td>cardSeq</td><td>Integer</td><td><input type="text" name="cardSeq" value="1"></td><td>決済に使用するカードの連番</td>');
    		$('#cardPass').append('<td>  </td><td>カードパスワード</td><td>cardPass</td><td>String</td><td><input type="text" name="cardPass" value=""></td><td>カード登録時に設定したパスワード</td>');
    	}
    });
</script>
