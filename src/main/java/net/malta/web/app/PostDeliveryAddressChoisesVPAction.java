/**
 * @author SB
 */
package net.malta.web.app;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import net.malta.beans.DeliveryAddressChoiseForm;
import net.malta.model.DeliveryAddress;
import net.malta.model.json.mapper.DeliveryAddressMapper;
import net.malta.service.purchase.IDeliveryAddressChoiseService;
import net.malta.web.model.PurchaseInfo;
import net.malta.web.utils.BeanUtil;
import net.malta.web.utils.JSONResponseUtil;
import net.malta.web.utils.SessionData;

// this actions updates the choises with the provided delivery address
public class PostDeliveryAddressChoisesVPAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DeliveryAddressChoiseForm deliveryAddressChoiseForm = (DeliveryAddressChoiseForm) form;
		
		DeliveryAddress deliveryAddress = updateDeliveryAddress(request, deliveryAddressChoiseForm);

		sendJSON(response, deliveryAddress);
		
		// updating the delivery address at choise level not needed now.
		
		return null;
	}

	private void sendJSON(HttpServletResponse response, DeliveryAddress deliveryAddress) throws IOException {
		DeliveryAddressMapper mapper = (DeliveryAddressMapper) BeanUtil.getBean("deliveryAddressMapper", 
				this.getServlet().getServletContext());
		
		net.malta.model.user.json.DeliveryAddress deliveryAddressJson = mapper.map(deliveryAddress, new net.malta.model.user.json.DeliveryAddress());
		
		JSONResponseUtil.writeResponseAsJSON(response, deliveryAddressJson);
	}

	private DeliveryAddress updateDeliveryAddress(HttpServletRequest request,
			DeliveryAddressChoiseForm deliveryAddressChoiseForm) {
		
		IDeliveryAddressChoiseService service = (IDeliveryAddressChoiseService) BeanUtil.getBean("deliveryAddressChoiseService", 
				this.getServlet().getServletContext());
		
		PurchaseInfo sessionPuchaseInfo = SessionData.getSessionPuchaseInfo(request);
		
		Integer deliveryAddressId = deliveryAddressChoiseForm.getDeliveryAddress(); 
				
		return service.updateChoisesWithDeliveryAddress(sessionPuchaseInfo.getPurchaseId(), sessionPuchaseInfo.getUserId(),
				deliveryAddressId);
	}
	
}