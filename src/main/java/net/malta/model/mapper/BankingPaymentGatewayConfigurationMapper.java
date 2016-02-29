package net.malta.model.mapper;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import com.getsecual.shop.payment.BankingPaymentGatewayConfiguration;
import com.getsecual.shop.payment.GMOPaymentWrapper;
import com.gmo_pg.g_pay.client.common.Method;

import net.malta.mapper.IMapper;
import net.malta.model.payment.PaymentInfo;

@Component
public class BankingPaymentGatewayConfigurationMapper implements IMapper<PaymentInfo, BankingPaymentGatewayConfiguration>{

	@Override
	public BankingPaymentGatewayConfiguration map(PaymentInfo paymentCardDetails, 
			BankingPaymentGatewayConfiguration paymentGatewayConfiguration) {
		try {
			// statics
			paymentGatewayConfiguration.setShopId(GMOPaymentWrapper.SHOP_ID);
			paymentGatewayConfiguration.setShopPass(GMOPaymentWrapper.SHOP_PASS);
			paymentGatewayConfiguration.setJobCd(GMOPaymentWrapper.JOB_CD_CAPTURE);
			paymentGatewayConfiguration.setTdFlag(GMOPaymentWrapper.TD_FLAG_USE);
			paymentGatewayConfiguration.setMethod(Method.IKKATU);
			paymentGatewayConfiguration.setPayTimes(1);
			paymentGatewayConfiguration.setSiteId(GMOPaymentWrapper.SITE_ID);
			paymentGatewayConfiguration.setSitePass(GMOPaymentWrapper.SITE_PASS);			
			BeanUtils.copyProperties(paymentGatewayConfiguration, paymentCardDetails);			
		} catch (Exception  e) {
			throw new RuntimeException(e);
		}
		return paymentGatewayConfiguration;
	}

}
