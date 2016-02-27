
package com.getsecual.shop.payment;

import static org.junit.Assert.*;
import mockit.Mock;
import mockit.MockUp;
import mockit.integration.junit4.JMockit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gmo_pg.g_pay.client.common.PaymentException;
import com.gmo_pg.g_pay.client.impl.PaymentClientImpl;
import com.gmo_pg.g_pay.client.input.EntryExecTranInput;
import com.gmo_pg.g_pay.client.output.EntryExecTranOutput;
import com.gmo_pg.g_pay.client.output.ExecTranOutput;


@RunWith(JMockit.class)
public class GMOPaymentWrapperTest {

    private GMOPaymentWrapper wrapper;

    @Before
    public void setUp() {
        wrapper = new GMOPaymentWrapper();
    }

    /**
     * 定数を参照するだけ
     */
    public void testExecutePayment1() {
    	String shopId = GMOPaymentWrapper.SHOP_ID;
    	String shopPass = GMOPaymentWrapper.SHOP_PASS;
    	String siteId = GMOPaymentWrapper.SITE_ID;
    	String sitePass = GMOPaymentWrapper.SITE_PASS;
    	String jobCd = GMOPaymentWrapper.JOB_CD_CAPTURE;
    	String tdFlagUse = GMOPaymentWrapper.TD_FLAG_USE;
    }

    /**
     * 正常終了のケース
     * @throws Exception
     */
    @Test
    public void testExecutePayment2() {

    	PaymentGatewayConfiguration paymentGatewayConfiguration = new BankingPaymentGatewayConfiguration();

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
			wrapper.executePayment(paymentGatewayConfiguration);
		} catch (Exception e) {
			fail();
		}

    }

    /**
     * PaymentExceptionがスローされるケース
     * @throws Exception
     */
    @Test
    public void testExecutePayment3() throws Exception {

    	PaymentGatewayConfiguration paymentGatewayConfiguration = new BankingPaymentGatewayConfiguration();

    	// モック不要

		try {
			wrapper.executePayment(paymentGatewayConfiguration);
		} catch (Exception e) {
			return;
		}
		// 正常終了は想定外
    	fail();
    }

    /**
     * その他、Exceptionがスローされるケース(PaymentExceptionでラップされること)
     * @throws Exception
     */
    @Test
    public void testExecutePayment4() throws Exception {

    	PaymentGatewayConfiguration paymentGatewayConfiguration = null;

    	// モック不要、nullを投げたらPaymentException以外がスローされてPaymentExceptionにラップされることを確認

		try {
			wrapper.executePayment(paymentGatewayConfiguration);
		} catch (Exception e) {
			if (e instanceof PaymentException) {
				// PaymentExceptionにラップされていたら正常
				return;
			}
		}
		// 正常終了とPaymentException以外の例外は想定外
    	fail();
    }

    @After
    public void checkExpectations() {
    }
}
