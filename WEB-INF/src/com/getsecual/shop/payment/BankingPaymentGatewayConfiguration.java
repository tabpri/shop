package com.getsecual.shop.payment;

import java.util.ArrayList;
import java.util.List;

import com.gmo_pg.g_pay.client.common.PaymentException;
import com.gmo_pg.g_pay.client.impl.PaymentClientImpl;
import com.gmo_pg.g_pay.client.input.EntryExecTranInput;
import com.gmo_pg.g_pay.client.output.EntryExecTranOutput;
import com.gmo_pg.g_pay.client.output.ErrHolder;
import com.gmo_pg.g_pay.client.output.SecureTranOutput;

/**
 * ◇カード番号を入力して決済する＜本人認証サービスを未使用＞
 * ◇カード番号を入力して決済する＜本人認証サービスを使用＞
 * ◇登録したカード情報で決済する＜本人認証サービスを未使用＞
 * ◇登録したカード情報で決済する＜本人認証サービスを使用＞
 * @author
 *
 */
public class BankingPaymentGatewayConfiguration extends EntryExecTranInput implements
		PaymentGatewayConfiguration {

	// エラー情報
	private List entryErrList = null;
	// 3Dセキュア認証結果
	private String PaRes = null;
	// 取引ID
	private String MD = null;
	// 3D認証サービスから結果がPOSTされるURL
	private String termUrl;
	// リダイレクトページHTML
	private String redirectContents;
	// ACS呼出判定
	private String acs;
	// 本人認証パスワード入力画面URL
	private String acsUrl;
	// 本人認証サービス要求電文
	private String paReq;

	/**
	 * 決済実行
	 * @throws com.gmo_pg.g_pay.client.common.PaymentException
	 */
	public void executePaymentGateway() throws PaymentException {
		// GMOPG
		PaymentClientImpl paymentClientImpl = new PaymentClientImpl();

		// 認証後決済(3D セキュア認証結果と取引IDが設定されている場合)
		if (PaRes != null && MD != null) {
			SecureTranOutput secureTranOutput = paymentClientImpl.doSecureTran(PaRes, MD);
			// 結果確認
			if (secureTranOutput.isErrorOccurred()) {
				entryErrList = new ArrayList();
				List errList = secureTranOutput.getErrList();
				for (int i = 0; errList.size() > i; i++) {
					ErrHolder errHolder = (ErrHolder)errList.get(i);
					entryErrList.add(new String[] {errHolder.getErrCode(), errHolder.getErrInfo()});
				}
			}
			return;
		}

		// 決済実行
		EntryExecTranOutput entryExecTranOutput = paymentClientImpl.doEntryExecTran(this);

		// 結果確認
		if (entryExecTranOutput.isErrorOccurred()) {
			entryErrList = new ArrayList();
			List errList = new ArrayList();
			errList.addAll(entryExecTranOutput.getEntryErrList());
			errList.addAll(entryExecTranOutput.getExecErrList());
			for (int i = 0; errList.size() > i; i++) {
				ErrHolder errHolder = (ErrHolder)errList.get(i);
				entryErrList.add(new String[] {errHolder.getErrCode(), errHolder.getErrInfo()});
			}
			return;
		}

		// 3D セキュア認証が必要な場合
		if ("1".equals(entryExecTranOutput.getAcs())) {
			// JSPでリダイレクト用
			this.acs = entryExecTranOutput.getAcs();
			this.paReq = entryExecTranOutput.getPaReq();
			this.MD = entryExecTranOutput.getMd();
			this.acsUrl = entryExecTranOutput.getAcsUrl();
			// 直接レスポンスに書き込み用
			this.redirectContents = paymentClientImpl.createRedirectPage("conf/RedirectPage.html",
					entryExecTranOutput.getExecTranOutput(), termUrl, "UTF-8");
		}

	}

	/**
	 * 3D セキュア認証結果を設定
	 */
	public void setPaRes(String PaRes) {
		this.PaRes = PaRes;
	}
	/**
	 * 取引IDを設定
	 */
	public void setMD(String MD) {
		this.MD = MD;
	}

	/**
	 * 3D認証結果返却URLを設定
	 */
	public void setTermUrl(String termUrl) {
		this.termUrl = termUrl;
	}

	/**
	 * リダイレクトページのHTMLを取得
	 * @return リダイレクトページのHTML
	 */
	public String getRedirectContents() {
		return redirectContents;
	}

	/**
	 * ACS呼出判定を取得
	 * @return ACS呼出判定
	 */
	public String getAcs() {
		return this.acs;
	}

	/**
	 * 本人認証パスワード入力画面URLを取得
	 * @return 本人認証パスワード入力画面URL
	 */
	public String getAcsUrl() {
		return this.acsUrl;
	}

	/**
	 * 本人認証サービス要求電文を取得
	 * @return 本人認証サービス要求電文
	 */
	public String getPaReq() {
		return this.paReq;
	}

	/**
	 * 取引IDを取得
	 * @return 取引ID
	 */
	public String getMD() {
		return this.MD;
	}

	/**
	 * エラー情報を取得
	 * @return エラー情報
	 */
	public List getErrList() {
		return this.entryErrList;
	}


}
