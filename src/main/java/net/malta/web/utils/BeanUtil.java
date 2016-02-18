package net.malta.web.utils;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import net.malta.model.json.mapper.DeliveryAddressesMapper;
import net.malta.model.json.mapper.PaymentMethodsMapper;
import net.malta.model.json.mapper.ProductsMapper;
import net.malta.model.json.mapper.PurchaseChoiseMapper;
import net.malta.model.json.mapper.PurchaseMapper;

public class BeanUtil {

	public static ProductsMapper getProductsMapper(ServletContext context) {
		return (ProductsMapper) getBean("productsMapper",context);
	}
	
	public static Object getBean(String name, ServletContext context) {
		ApplicationContext applicationContext = getApplicationContext(context);
		return applicationContext.getBean(name);
	}

	public static ApplicationContext getApplicationContext(ServletContext context) {
		return WebApplicationContextUtils.getWebApplicationContext(context);		
	}

	public static PurchaseChoiseMapper getPurchaseChoiseMapper(ServletContext context) {
		return (PurchaseChoiseMapper) getBean("purchaseChoiseMapper", context);
	}

	public static PurchaseMapper getPurchaseMapper(ServletContext context) {
		return (PurchaseMapper) getBean("purchaseMapper", context);
	}

	public static PaymentMethodsMapper getPaymentMethodsMapper(ServletContext context) {
		return (PaymentMethodsMapper) getBean("paymentMethodsMapper", context);
	}

	public static DeliveryAddressesMapper getDeliveryAddressesMapper(ServletContext context) {
		return (DeliveryAddressesMapper) getBean("deliveryAddressesMapper", context);
	}
}