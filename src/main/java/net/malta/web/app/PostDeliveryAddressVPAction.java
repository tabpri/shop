package net.malta.web.app;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import net.malta.beans.DeliveryAddressForm;
import net.malta.beans.mapper.DeliveryAddressFormMapper;
import net.malta.model.DeliveryAddress;
import net.malta.model.DeliveryAddressImpl;
import net.malta.model.json.mapper.DeliveryAddressMapper;
import net.malta.model.validator.ValidationException;
import net.malta.service.user.IDeliveryAddressService;
import net.malta.web.model.PublicUserInfo;
import net.malta.web.model.PurchaseInfo;
import net.malta.web.utils.BeanUtil;
import net.malta.web.utils.JSONResponseUtil;
import net.malta.web.utils.SessionData;


public class PostDeliveryAddressVPAction extends Action{
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws Exception{
		try {
			
			DeliveryAddressForm deliveryAddressform = (DeliveryAddressForm) form;
			
			DeliveryAddress deliveryAddress = mapDeliveryAddressForm(deliveryAddressform);
			
			persist(req,deliveryAddress);
			
			sendJSON(res,deliveryAddress);
			
		} catch (ValidationException ve) {
			JSONResponseUtil.sendErrorJSON(res, ve.getErrors());
		}
		return null;
	}

	private DeliveryAddress mapDeliveryAddressForm(DeliveryAddressForm deliveryAddressform) {
		DeliveryAddressFormMapper deliverAddressFormMapper = (DeliveryAddressFormMapper) BeanUtil.getBean("deliveryAddressFormMapper", 
				this.getServlet().getServletContext());				
		DeliveryAddress deliveryAddress = deliverAddressFormMapper.map(deliveryAddressform, new DeliveryAddressImpl());
		return deliveryAddress;
	}
	

	private void persist(HttpServletRequest req,DeliveryAddress deliveryAddress) {
		
		PurchaseInfo sessionPuchaseInfo = SessionData.getSessionPuchaseInfo(req);
		Integer userId = sessionPuchaseInfo.getUserId();

		IDeliveryAddressService deliveryAddressService = (IDeliveryAddressService) BeanUtil.getBean("deliveryAddressService", 
				this.getServlet().getServletContext());
		
		if ( deliveryAddress.getId() != null ) {
			deliveryAddressService.updateDeliveryAddress(userId, deliveryAddress);			
		} else {
			deliveryAddressService.addDeliveryAddress(sessionPuchaseInfo.getUserId(), deliveryAddress);
		}
	}

	private void sendJSON(HttpServletResponse response,DeliveryAddress deliveryAddress) throws IOException {
		DeliveryAddressMapper mapper =  (DeliveryAddressMapper) BeanUtil.getBean("deliveryAddressMapper", 
				this.getServlet().getServletContext());
		net.malta.model.user.json.DeliveryAddress deliveryAddressJSON = mapper.map(deliveryAddress, 
				new net.malta.model.user.json.DeliveryAddress());
		JSONResponseUtil.writeResponseAsJSON(response, deliveryAddressJSON);
	}
	
}