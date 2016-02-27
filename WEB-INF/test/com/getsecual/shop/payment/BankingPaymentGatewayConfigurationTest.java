
package com.getsecual.shop.payment;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import mockit.Mock;
import mockit.MockUp;
import mockit.integration.junit4.JMockit;

import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gmo_pg.g_pay.client.common.Method;
import com.gmo_pg.g_pay.client.common.PaymentException;
import com.gmo_pg.g_pay.client.impl.PaymentClientImpl;
import com.gmo_pg.g_pay.client.input.EntryExecTranInput;
import com.gmo_pg.g_pay.client.output.EntryExecTranOutput;
import com.gmo_pg.g_pay.client.output.EntryTranOutput;
import com.gmo_pg.g_pay.client.output.ErrHolder;
import com.gmo_pg.g_pay.client.output.ExecTranOutput;
import com.gmo_pg.g_pay.client.output.SecureTranOutput;


@RunWith(JMockit.class)
public class BankingPaymentGatewayConfigurationTest {

	private final String SHOP_ID = "tshop00021241";
	private final String SHOP_PASS = "7dgkrxz1";
	private final String JOB_CD = "CAPTURE";
	private final String ORDER_ID = "11111111111";
	private final String SITE_ID = "tsite00019730";
	private final String SITE_PASS = "urxthwt2";
	private final Integer AMOUNT = new Integer(1000);
	private final Integer TAX = new Integer(500);

	private final String HTTP_ACCEPT = "text/html, text/plain, text/sgml, */*;q=0.01";
	private final String HTTP_USER_AGENT = "Mozilla/5.0 (Linux; U; Android 3.2.1; ja-jp; "
			+ "Transformer TF101 Build/HTK75) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13";

	/** 正常カード */
	private final String CARD_NO1 = "4111111111111111";
	/** 正常カード(本人認証) */
	private final String CARD_NO2 = "4123450131003312";
	/** 異常カード(取扱不可) */
	private final String CARD_NO3 = "4999000000000012";
	private final String EXPIRE = "1705";

	private final String TERMURL = "http://localhost:8080/PostPurchaseVPChoosingPaymentMethod.do";

    private BankingPaymentGatewayConfiguration configuration;
    private Mockery mockery;

    @Before
    public void setUp() {
        configuration = new BankingPaymentGatewayConfiguration();
    }

    /**
     * カード情報で決済する（正常系テスト）
     * @throws PaymentException
     */
    @Test
    public void testeExecutePaymentGateway1() throws Exception {

    	configuration.setShopId(SHOP_ID);
    	configuration.setShopPass(SHOP_PASS);
    	configuration.setOrderId(ORDER_ID);
    	configuration.setJobCd(JOB_CD);
    	configuration.setAmount(AMOUNT);
    	configuration.setTax(TAX);
    	configuration.setMethod(Method.IKKATU);
    	// カード情報
    	configuration.setCardNo(CARD_NO1);
    	configuration.setExpire(EXPIRE);

    	configuration.setTermUrl(TERMURL);

    	new MockUp<PaymentClientImpl> () {
    		@Mock public void $init() {
    			this.getMockInstance();
    		}
		};

		new MockUp<PaymentClientImpl> () {
    		@Mock public EntryExecTranOutput doEntryExecTran(EntryExecTranInput input) {
    			// 戻り値設定
    			EntryExecTranOutput entryExecTranOutput = new EntryExecTranOutput();
    			return entryExecTranOutput;
    		}
    		@Mock public String createRedirectPage(String pagePath, ExecTranOutput execResult, String termUrl, String encode) throws PaymentException {
    			// このルートには入らないはずですが、もし入ってたらエラーをスローする
    			throw new PaymentException();
    		}
		};

    	try {
    		configuration.executePaymentGateway();
    	} catch (Exception e) {
    		fail();
    	}

    }

    /**
     * カード情報で決済する（正常系テスト）
     * 本人認証用HTMLが作成されてくること、左記をJSPで実施することになった場合に接続するための値が出力されること。
     * @throws PaymentException
     */
    @Test
    public void testeExecutePaymentGateway2() throws Exception {

    	configuration.setShopId(SHOP_ID);
    	configuration.setShopPass(SHOP_PASS);
    	configuration.setOrderId(ORDER_ID);
    	configuration.setJobCd(JOB_CD);
    	configuration.setAmount(AMOUNT);
    	configuration.setTax(TAX);
    	configuration.setMethod(Method.IKKATU);
    	// セキュア使用フラグ
    	configuration.setTdFlag("1");
    	configuration.setHttpAccept(HTTP_ACCEPT);
    	configuration.setHttpUserAgent(HTTP_USER_AGENT);
    	// カード情報
    	configuration.setCardNo(CARD_NO2);
    	configuration.setExpire(EXPIRE);

    	configuration.setTermUrl(TERMURL);

    	new MockUp<PaymentClientImpl> () {
    		@Mock public void $init() {
    			this.getMockInstance();
    		}
		};

		new MockUp<PaymentClientImpl> () {
    		@Mock public EntryExecTranOutput doEntryExecTran(EntryExecTranInput input) {
    			// 戻り値設定
    			EntryExecTranOutput entryExecTranOutput = new EntryExecTranOutput();
    			entryExecTranOutput.setAcs("1"); // ACS認証要
    			entryExecTranOutput.setPaReq("12345");
    			entryExecTranOutput.setMd("12345");
    			entryExecTranOutput.setAcsUrl("https://pt.com/3dsecure");
    			return entryExecTranOutput;
    		}
    		@Mock public String createRedirectPage(String pagePath, ExecTranOutput execResult, String termUrl, String encode) {
    			return "<html><body><p>本人認証要のHTMLコンテンツ、呼び出しと同時にポストされます</p></body></html>";
    		}
		};

    	try {
    		configuration.executePaymentGateway();
    	} catch (Exception e) {
    		fail();
    	}

    	// HTMLコンテンツを直接出力して決済センターに接続する場合
    	assertNotNull(configuration.getRedirectContents());
    	// JSPを利用して決済センターに接続する場合
    	assertNotNull(configuration.getAcs());
    	assertNotNull(configuration.getPaReq());
    	assertNotNull(configuration.getMD());
    	assertNotNull(configuration.getAcsUrl());

    }

    /**
     * カード情報で決済する（異常系テスト）
     * @throws PaymentException
     */
    @Test
    public void testeExecutePaymentGateway3() throws Exception {

    	configuration.setShopId(SHOP_ID);
    	configuration.setShopPass(SHOP_PASS);
    	configuration.setOrderId(ORDER_ID);
    	configuration.setJobCd(JOB_CD);
    	configuration.setAmount(AMOUNT);
    	configuration.setTax(TAX);
    	configuration.setMethod(Method.IKKATU);
    	// カード情報
    	configuration.setCardNo(CARD_NO3);
    	configuration.setExpire(EXPIRE);

    	configuration.setTermUrl(TERMURL);

    	new MockUp<PaymentClientImpl> () {
    		@Mock public void $init() {
    			this.getMockInstance();
    		}
		};

		new MockUp<PaymentClientImpl> () {
    		@Mock public EntryExecTranOutput doEntryExecTran(EntryExecTranInput input) {
    			// エラーコード生成
    			EntryExecTranOutput entryExecTranOutput = new EntryExecTranOutput();
    			EntryTranOutput entryTranOutput = new EntryTranOutput();
    			ExecTranOutput execTranOutput = new ExecTranOutput();
    			List entryErrList = new ArrayList();
    			List execErrList = new ArrayList();
    			ErrHolder errHolder = new ErrHolder();
    			errHolder.setErrCode("E01");
    			errHolder.setErrInfo("E999999999");
    			entryErrList.add(errHolder);
    			execErrList.add(errHolder);
    			entryTranOutput.setErrList(entryErrList);
    			execTranOutput.setErrList(execErrList);
    			entryExecTranOutput.setEntryTranOutput(entryTranOutput);
    			entryExecTranOutput.setExecTranOutput(execTranOutput);
    			return entryExecTranOutput;
    		}
		};

    	try {
    		configuration.executePaymentGateway();
    	} catch (Exception e) {
    		fail();
    	}

    	// エラーリストが返却されていたら正常
    	assertNotNull(configuration.getErrList());

    }

    /**
     * 3Dセキュア認証後決済テスト(正常系ルート)
     * @throws PaymentException
     */
    @Test
    public void testeExecutePaymentGateway4() {

    	configuration.setPaRes("PARES");
    	configuration.setMD("MD");

    	new MockUp<PaymentClientImpl> () {
    		@Mock public void $init() {
    			this.getMockInstance();
    		}
		};

		new MockUp<PaymentClientImpl> () {
    		@Mock public SecureTranOutput doSecureTran(String pares, String md) {
    			return new SecureTranOutput();
    		}
		};

    	try {
			configuration.executePaymentGateway();
		} catch (PaymentException e) {
			fail();
		}

    }

    /**
     * 3Dセキュア認証後決済テスト(異常系ルート)
     * @throws PaymentException
     */
    @Test
    public void testeExecutePaymentGateway5() throws Exception {

    	configuration.setPaRes("PARES");
    	configuration.setMD("MD");

    	new MockUp<PaymentClientImpl> () {
    		@Mock public void $init() {
    			this.getMockInstance();
    		}
		};

		new MockUp<PaymentClientImpl> () {
    		@Mock public SecureTranOutput doSecureTran(String pares, String md) {
    			// エラーコード生成
    			SecureTranOutput secureTranOutput = new SecureTranOutput();
    			List errList = new ArrayList();
    			ErrHolder errHolder = new ErrHolder();
    			errHolder.setErrCode("E01");
    			errHolder.setErrInfo("E101010101");
    			errList.add(errHolder);
    			secureTranOutput.setErrList(errList);
    			return secureTranOutput;
    		}
		};

		try {
			configuration.executePaymentGateway();
		} catch (PaymentException e) {
			fail();
		}

		// エラーリスト確認
		assertNotNull(configuration.getErrList());

    }

    @After
    public void checkExpectations() {
    }
}
