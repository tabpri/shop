package com.getsecual.shop.payment;

import com.gmo_pg.g_pay.client.common.PaymentException;

/**
 * GMOPGラッパークラス
 *
 * @author
 *
 */
public class GMOPaymentWrapper {

	/** ショップID */
	public static final String SHOP_ID = "tshop00021241";
	/** ショップパスワード */
	public static final String SHOP_PASS = "7dgkrxz1";
	/** サイトID */
	public static final String SITE_ID = "tsite00019730";
	/** サイトパスワード */
	public static final String SITE_PASS = "urxthwt2";
	/** 処理区分 */
	public static final String JOB_CD_CAPTURE = "CAPTURE";
	/** 3Dセキュア使用フラグ */
	public static final String TD_FLAG_USE = "1";

	/**
	 * 支払い処理実行
	 *
	 * @param com.getsecual.shop.payment.PaymentGatewayConfiguration
	 * @throws com.gmo_pg.g_pay.client.common.PaymentException
	 */
	public void executePayment(PaymentGatewayConfiguration paymentGatewayConfiguration) throws PaymentException {
		// 決済実行、実行内容はインターフェースの実装クラスによる
		try {
			paymentGatewayConfiguration.executePaymentGateway();
		} catch (PaymentException paymentException) {
			// APIのエラーはそのままスローする
			throw paymentException;
		} catch (Exception exception) {
			// その他のエラーはラップしてスローする
			throw new PaymentException(exception);
		}
	}

}
